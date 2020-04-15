package com.example.aq.Domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface codeInfoMapper {
    @Insert("insert into code_info (user_account) values (#{user_account})")
    public void add(codeInfo c);

}
