package com.tlias.mapper;

import com.tlias.pojo.EmpLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLoginLogMapper {

    @Insert("INSERT INTO tlias_emp_login_log(username, password, login_time, is_success, jwt, cost_time) " +
            "VALUES(#{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime})")
    void insert(EmpLoginLog empLoginLog);
}