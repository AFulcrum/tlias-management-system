package com.tlias.mapper;

import com.tlias.pojo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    @Insert("insert into tlias_student(name, no, gender, phone,id_card, is_college, address, degree, graduation_date,clazz_id, create_time, update_time) VALUES " +
            "(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    List<Student> list(String name, Integer degree, Integer clazzId);

    @Select("select * from tlias_student where id = #{id}")
    Student getInfo(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    @Update("update tlias_student set violation_count = violation_count + 1 , violation_score = violation_score + #{score} , update_time = now() where id = #{id}")
    void updateViolation(Integer id, Integer score);

    @MapKey("name")
    List<Map> countStudentDegreeData();

    @Select("select c.name cname , count(s.id) scount from tlias_clazz c  left join tlias_student s on s.clazz_id = c.id group by c.name order by count(s.id) desc ")
    List<Map<String, Object>> getStudentCount();

    @Select("select count(*) from tlias_student where clazz_id = #{id}")
    Integer countByClazzId(Integer id);

    void insertBatch(List<Student> studentList);
}
