package com.tlias.controller;

import com.tlias.anno.Log;
import com.tlias.pojo.Clazz;
import com.tlias.pojo.PageResult;
import com.tlias.pojo.Result;
import com.tlias.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping
    public Result page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page ,
                       @RequestParam(defaultValue = "10")Integer pageSize){
        PageResult pageResult = clazzService.page(name,begin,end,page,pageSize);
        return  Result.success(pageResult);
    }

    @Log
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        Clazz clazz=clazzService.getInfo(id);
        return Result.success(clazz);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        clazzService.deleteById(id);
        return Result.success();
    }

    @Log
    @GetMapping("/list")
    public Result findAll(){
        List<Clazz> clazzList=clazzService.findAll();
        return Result.success(clazzList);
    }
}
