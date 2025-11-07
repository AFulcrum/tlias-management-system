package com.tlias.service.impl;

import com.tlias.mapper.EmpMapper;
import com.tlias.mapper.StudentMapper;
import com.tlias.pojo.ClazzCountOption;
import com.tlias.pojo.JobOption;
import com.tlias.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list= empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzCountOption getStudentCountData() {
        List<Map<String,Object>> countList= studentMapper.getStudentCount();
        if(!CollectionUtils.isEmpty(countList)){
            List<Object> clazzList = countList.stream().map(dataMap -> {
                    return dataMap.get("cname");
            }).toList();
            List<Object> dataList = countList.stream().map(dataMap -> {
                return dataMap.get("scount");
            }).toList();
            return new ClazzCountOption(clazzList, dataList);
        }
        return null;
    }
}
