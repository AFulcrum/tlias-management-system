package com.tlias.service;

import com.tlias.pojo.Clazz;
import com.tlias.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    void save(Clazz clazz);

    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> findAll();
}
