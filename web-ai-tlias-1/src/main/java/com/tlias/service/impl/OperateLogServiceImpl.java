package com.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.OperateLogMapper;
import com.tlias.pojo.OperateLog;
import com.tlias.pojo.OperateLogQueryParam;
import com.tlias.pojo.PageResult;
import com.tlias.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public void save(OperateLog operateLog) {
        operateLogMapper.insert(operateLog);
    }

    @Override
    public PageResult page(OperateLogQueryParam queryParam) {
        PageHelper.startPage(queryParam.getPage(), queryParam.getPageSize());
        List<OperateLog> logList = operateLogMapper.list(queryParam);
        Page<OperateLog> page = (Page<OperateLog>) logList;
        return new PageResult(page.getTotal(), page.getResult());
    }
}