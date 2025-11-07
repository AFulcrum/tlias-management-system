package com.tlias.mapper;

import com.tlias.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {

    @Insert("insert into tlias_clazz VALUES (null,#{name},#{room},#{beginDate},#{endDate},#{masterId}, #{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    @Select("select * from tlias_clazz where  id = #{id}")
    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    @Delete("delete from tlias_clazz where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from tlias_clazz")
    List<Clazz> findAll();
}
