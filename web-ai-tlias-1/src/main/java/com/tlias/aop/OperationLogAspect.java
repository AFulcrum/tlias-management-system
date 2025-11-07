package com.tlias.aop;

import com.tlias.mapper.OperateLogMapper;
import com.tlias.pojo.OperateLog;
import com.tlias.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.tlias.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("=== 操作日志切面开始执行 ===");

        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        try {
            // 构建日志实体
            OperateLog olog = new OperateLog();
            Integer currentUserId = getCurrentUserId();
            olog.setOperateEmpId(currentUserId);
            olog.setOperateTime(LocalDateTime.now());
            olog.setClassName(joinPoint.getTarget().getClass().getName());
            olog.setMethodName(joinPoint.getSignature().getName());
            olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
            olog.setReturnValue(result != null ? result.toString() : "void");
            olog.setCostTime(costTime);

            // 保存日志
            log.info("记录操作日志: operateEmpId={}, className={}, methodName={}",
                    currentUserId, olog.getClassName(), olog.getMethodName());
            operateLogMapper.insert(olog);
            log.info("操作日志保存成功，ID: {}", olog.getId());

        } catch (Exception e) {
            log.error("保存操作日志失败", e);
        }

        log.info("=== 操作日志切面执行完成 ===");
        return result;
    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}