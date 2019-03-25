package com.github.qia.service;

import com.github.qia.pojo.Question;
import com.github.qia.pojogroup.FullQuestion;

import entity.PageResult;

public interface QuestionService {

	void add(Question question);

	PageResult findList(int pageNum, int pageSize);
	
	FullQuestion findOne(Long id,int pageNum,int pageSize);

	PageResult findAll();

}
