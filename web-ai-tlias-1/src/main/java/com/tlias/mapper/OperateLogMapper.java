package com.tlias.mapper;

import com.tlias.pojo.OperateLog;
import com.tlias.pojo.OperateLogQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tlias_operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    void insert(OperateLog log);

    List<OperateLog> list(OperateLogQueryParam queryParam);
}