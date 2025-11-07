package com.tlias.controller;

import com.tlias.pojo.OperateLogQueryParam;
import com.tlias.pojo.PageResult;
import com.tlias.pojo.Result;
import com.tlias.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping("/page")
    public Result page(OperateLogQueryParam queryParam) {
        log.info("分页查询操作日志: {}", queryParam);
        PageResult pageResult = operateLogService.page(queryParam);
        return Result.success(pageResult);
    }
}