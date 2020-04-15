package com.example.aq.Domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface KnowledgeMapper {
    @Select("Select knowledge_name from knowledge where knowledge_id=#{knowledge_id}")
    public String name(int knowledge_id);
}
