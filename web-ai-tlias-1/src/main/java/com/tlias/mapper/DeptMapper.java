package com.tlias.mapper;

import com.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from tlias_dept order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from tlias_dept where id=#{id}")
    void deleteById(Integer id);

    @Insert("insert into tlias_dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from tlias_dept where id=#{id}")
    Dept getById(Integer id);

    @Update("update tlias_dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}
