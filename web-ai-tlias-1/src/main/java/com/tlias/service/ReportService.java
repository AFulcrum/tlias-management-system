package com.tlias.service;

import com.tlias.pojo.ClazzCountOption;
import com.tlias.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();

    List<Map> getStudentDegreeData();

    ClazzCountOption getStudentCountData();
}
