package com.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tlias.mapper.StudentMapper;
import com.tlias.pojo.ClazzCountOption;
import com.tlias.pojo.PageResult;
import com.tlias.pojo.Student;
import com.tlias.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Student> dataList=studentMapper.list(name,degree,clazzId);
        Page<Student> p=(Page<Student>)dataList;
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.updateViolation(id, score);
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String, Object>> countList = studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            List<Object> clazzList = countList.stream().map(map -> {
                return map.get("cname");
            }).toList();

            List<Object> dataList = countList.stream().map(map -> {
                return map.get("scount");
            }).toList();

            return new ClazzCountOption(clazzList, dataList);
        }
        return null;
    }


}
