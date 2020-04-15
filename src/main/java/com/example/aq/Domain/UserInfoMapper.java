package com.example.aq.Domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.python.antlr.op.Sub;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    @Insert("Insert into user(username,password,name,tea_account,total_submit,accepted,accuracy,score) values (#{username},#{password},#{name},#{tea_account},0,0,0,0)")
    public void add(UserInfo userinfo);

    @Select("Select * from user where username=#{username} and password=#{password}")
    public UserInfo find(UserInfo userInfo);

    @Select("Select * from user where account=#{account}")
    public List<UserInfo> findbyid(UserInfo userInfo);

    @Select("select count(*) from user where score >=(select score from user where account = #{account})")
    public int rank(UserInfo userInfo);

    @Select("select * from user where tea_account is not null order by score DESC")
    public List<UserInfo> zongbang();

    @Select("select name from user where account=#{account}")
    public String name(UserInfo userInfo);

    @Select("select total_submit from user where account=#{account}")
    public int zongtijiao(UserInfo userInfo);

    @Update("update user set total_submit=#{total_submit} where account=#{account}")
    public void gengxin(UserInfo userInfo);

    @Select("select accepted from user where account=#{account}")
    public int accept(UserInfo userInfo);

    @Update("update user set accepted=#{accepted} where account=#{account}")
    public void accepted(UserInfo userInfo);

    @Select("select score from user where account=#{account}")
    public int sco(UserInfo userInfo);

    @Update("update user set score=#{score} where account=#{account}")
    public void score(UserInfo userInfo);

    @Update("update user set password=#{password} where username=#{username}")
    public void gaimima(UserInfo userInfo);

    @Update("update user set password=#{password} where account=#{account}")
    public void gmima(UserInfo userInfo);

}
