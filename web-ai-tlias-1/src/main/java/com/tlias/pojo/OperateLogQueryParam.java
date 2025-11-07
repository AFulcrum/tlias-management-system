package com.tlias.pojo;

import lombok.Data;

@Data
public class OperateLogQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String operateEmpName;
}