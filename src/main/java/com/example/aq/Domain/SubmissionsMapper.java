package com.example.aq.Domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SubmissionsMapper {
    @Insert("Insert into submissions (uid,pid) values(#{uid},#{pid})")
    public void add(Submissions submissions);

    @Select("Select count(*) from submissions where uid=#{uid} and pid=#{pid}")
    public int tot(Submissions submissions);

    @Update("update submissions set times=#{times} where uid=#{uid} and pid=#{pid} and times is null")
    public void times(Submissions submissions);

    @Select("Select max(status) from submissions where uid=#{uid} and pid=#{pid}")
    public int sta(Submissions submissions);

    @Update("Update submissions set status=#{status} where status=-1 and uid=#{uid} and pid=#{pid}")
    public void status(Submissions submissions);

    @Select("Select * from submissions")
    public List<Submissions> all();


}
