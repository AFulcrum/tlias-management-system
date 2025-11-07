package com.tlias.controller;

import com.tlias.anno.Log;
import com.tlias.pojo.PageResult;
import com.tlias.pojo.Result;
import com.tlias.pojo.Student;
import com.tlias.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Result save(@RequestBody Student student){
        studentService.save(student);
        return Result.success();
    }

    @GetMapping
    public Result page(String name ,
                       Integer degree,
                       Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        PageResult pageResult= studentService.page(name,degree,clazzId,page,pageSize);
        return Result.success(pageResult);
    }

    @Log
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        studentService.delete(ids);
        return Result.success();
    }

    @Log
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id,
                                   @PathVariable Integer score){
        studentService.violationHandle(id,score);
        return Result.success();
    }
}
