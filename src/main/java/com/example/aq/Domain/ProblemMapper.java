package com.example.aq.Domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProblemMapper {
    @Select("select * from problem")
    public List<Problem> all();

    @Update("update problem set title=#{title},description=#{description},difficulty_level=#{difficulty_level},knowledge_level=#{knowledge_level} where problem_id=#{problem_id}")
    public void geng(Problem problem);

    @Select("select description from problem where problem_id=#{problem_id}")
    public String Byid(Problem problem);

    @Insert("insert into problem (title,description,difficulty_level,knowledge_level) values (#{title},#{description},#{difficulty_level},#{knowledge_level})")
    public void ins(Problem problem);

}
