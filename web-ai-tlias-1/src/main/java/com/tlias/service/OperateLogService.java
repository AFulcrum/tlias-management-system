package com.tlias.service;

import com.tlias.pojo.OperateLog;
import com.tlias.pojo.OperateLogQueryParam;
import com.tlias.pojo.PageResult;

public interface OperateLogService {
    void save(OperateLog operateLog);
    PageResult page(OperateLogQueryParam queryParam);
}