package com.tlias.service;

import com.tlias.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;

public interface EmpLogService {

    void insertLog(EmpLog empLog);
}
