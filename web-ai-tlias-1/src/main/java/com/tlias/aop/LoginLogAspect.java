package com.tlias.aop;

import com.tlias.mapper.EmpLoginLogMapper;
import com.tlias.pojo.Emp;
import com.tlias.pojo.EmpLoginLog;
import com.tlias.pojo.LoginInfo;
import com.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@Aspect
public class LoginLogAspect {

    @Autowired
    private EmpLoginLogMapper empLoginLogMapper;

    @Around("execution(* com.tlias.service.impl.EmpServiceImpl.login(..))")
    public Object recordLoginLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法参数 - Emp对象
        Object[] args = joinPoint.getArgs();
        Emp emp = null;
        String username = null;
        String password = null;

        // 解析参数，第一个参数是Emp对象
        if (args.length > 0 && args[0] instanceof Emp) {
            emp = (Emp) args[0];
            username = emp.getUsername();
            password = emp.getPassword();
        } else {
            log.warn("登录方法参数类型不符合预期，无法记录日志");
        }

        // 记录开始时间
        long startTime = System.currentTimeMillis();
        LocalDateTime loginTime = LocalDateTime.now();

        Object result = null;
        boolean isSuccess = false;
        String jwt = null;

        try {
            // 执行原始方法
            result = joinPoint.proceed();

            // 判断登录是否成功
            if (result instanceof Result) {
                Result loginResult = (Result) result;
                if (loginResult.getCode() == 1) { // 假设code=1表示成功
                    isSuccess = true;
                    // 获取JWT令牌
                    if (loginResult.getData() != null && loginResult.getData() instanceof LoginInfo) {
                        LoginInfo loginInfo = (LoginInfo) loginResult.getData();
                        jwt = loginInfo.getToken(); // 假设LoginInfo中有getToken方法
                    }
                }
            }

        } catch (Exception e) {
            // 登录失败
            isSuccess = false;
            throw e; // 重新抛出异常，保持原有行为
        } finally {
            // 计算耗时
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 记录日志到数据库
            try {
                if (username != null) { // 只有成功获取到用户名时才记录日志
                    EmpLoginLog loginLog = new EmpLoginLog();
                    loginLog.setUsername(username);
                    loginLog.setPassword(password); // 注意：实际生产中建议对密码进行脱敏处理
                    loginLog.setLoginTime(loginTime);
                    loginLog.setIsSuccess(isSuccess ? (short) 1 : (short) 0);
                    loginLog.setJwt(jwt);
                    loginLog.setCostTime(costTime);

                    empLoginLogMapper.insert(loginLog);

                    log.info("登录日志记录成功 - 用户名: {}, 是否成功: {}, 耗时: {}ms",
                            username, isSuccess ? "是" : "否", costTime);
                }
            } catch (Exception e) {
                log.error("记录登录日志失败", e);
                // 这里不抛出异常，避免影响主要业务流程
            }
        }

        return result;
    }
}