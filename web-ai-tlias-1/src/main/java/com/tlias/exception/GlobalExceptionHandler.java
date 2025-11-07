package com.tlias.exception;

import com.tlias.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("系统异常: ", e);
        return Result.error("系统异常，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("数据重复异常: ", e);
        String msg=e.getMessage();
        int i=msg.indexOf("Duplicate entry");
        String errMsg=msg.substring(i);
        String[] arr=msg.split(" ");
        return Result.error(arr[2] + " 已存在");
    }
}
