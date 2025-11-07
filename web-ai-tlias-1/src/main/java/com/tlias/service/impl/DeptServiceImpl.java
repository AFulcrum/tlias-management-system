package com.tlias.service.impl;

import com.tlias.exception.BusinessException;
import com.tlias.mapper.DeptMapper;
import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Dept;
import com.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        //1. 判断部门下是否有员工， 如果有， 需要提示错误信息
        Integer count = empMapper.countByDeptId(id);
        if(count > 0){
            throw new BusinessException("部门下有员工， 不能删除");
        }

        //2. 删除部门
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
