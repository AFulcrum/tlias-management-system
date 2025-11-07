package com.tlias.service;

import com.tlias.pojo.Emp;
import com.tlias.pojo.EmpQueryParam;
import com.tlias.pojo.LoginInfo;
import com.tlias.pojo.PageResult;

import java.util.List;

public interface EmpService {

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp) throws Exception;

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
