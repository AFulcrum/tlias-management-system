package com.tlias.controller;

import com.tlias.anno.Log;
import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据ID删除部门: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    @Log
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询部门: {}", id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}
