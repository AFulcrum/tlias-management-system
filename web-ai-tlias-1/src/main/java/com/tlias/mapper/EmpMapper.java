package com.tlias.mapper;

import com.tlias.pojo.Emp;
import com.tlias.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tlias_emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username}, #{name}, #{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    @Select("select id, username, password, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time from tlias_emp")
    List<Emp> findAll();

    @Select("select count(*) from tlias_emp where dept_id = #{deptId}")
    Integer countByDeptId(Integer deptId);

    @Select("select id, username, name from tlias_emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
