package com.github.qia.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdviceMapper {

	@Insert({ "insert into advice(content, username,create_time) values(#{content}, #{username}, #{create_time, jdbcType=TIMESTAMP})" })
	void insert(@Param("content")String content,@Param("username") String username,@Param("create_time") Date date);

}
