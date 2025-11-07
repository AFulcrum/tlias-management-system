package com.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.ClazzMapper;
import com.tlias.mapper.StudentMapper;
import com.tlias.pojo.Clazz;
import com.tlias.pojo.PageResult;
import com.tlias.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Clazz> dataList=clazzMapper.list(name,begin,end);
        Page<Clazz> p=(Page<Clazz>)dataList;
        return new PageResult(p.getTotal(),p);
    }

    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getInfo(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void deleteById(Integer id) {
        Integer count=studentMapper.countByClazzId(id);
        if(count>0){
            throw new RuntimeException("该班级下有学生，不能删除");
        }
        clazzMapper.deleteById(id);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
