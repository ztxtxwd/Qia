package com.github.qia.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.qia.mapper.CommentMapper;
import com.github.qia.mapper.QuestionMapper;
import com.github.qia.pojo.Comment;
import com.github.qia.pojo.CommentExample;
import com.github.qia.pojo.CommentExample.Criteria;
import com.github.qia.pojo.Question;
import com.github.qia.pojo.QuestionExample;
import com.github.qia.pojogroup.FullQuestion;
import com.github.qia.service.QuestionService;

import entity.PageResult;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public void add(Question question) {
		// TODO Auto-generated method stub
		question.setDate(new Date());
		question.setUpdateTime(new Date());
		questionMapper.insert(question);
	}

	@Override
	public PageResult findList(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		
		List<Question> questions=questionMapper.selectNew(pageSize);
		
		return new PageResult(pageSize, questions);
	}

	@Override
	public FullQuestion findOne(Long id,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		Question question=questionMapper.selectByPrimaryKey(id);
		PageHelper.startPage(pageNum, pageSize);
		CommentExample example=new CommentExample();
		Criteria criteria=example.createCriteria();
		criteria.andQidEqualTo(id);
		Page<Comment> comments=(Page<Comment>) commentMapper.selectByExample(example);
		
		FullQuestion fullQuestion=new FullQuestion();
		fullQuestion.setQuestion(question);
		fullQuestion.setComments(comments);
		fullQuestion.setTotal((int) comments.getTotal());
		return fullQuestion;
	}

	@Override
	public PageResult findAll() {
		// TODO Auto-generated method stub
		List<Question> questions=questionMapper.selectByExample(null);
		Collections.reverse(questions);
		return new PageResult(-1, questions);
	}
	
	

}
