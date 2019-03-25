package com.github.qia.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.qia.mapper.AdviceMapper;
import com.github.qia.mapper.CommentMapper;
import com.github.qia.mapper.QuestionMapper;
import com.github.qia.pojo.Comment;
import com.github.qia.service.CommentService;
import com.github.qia.util.HtmlConvert;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private AdviceMapper adviceMapper;
	
	@Override
	public void add(Comment comment) {
		// TODO Auto-generated method stub
		comment.setDate(new Date());
		comment.setContent(HtmlConvert.Text2Html(comment.getContent()));
		commentMapper.insert(comment);
		questionMapper.updateCommentNum(comment.getQid());
		questionMapper.updateUpdateTime(new Date(),comment.getQid());
	}

	@Override
	public void advise(String content, String username) {
		// TODO Auto-generated method stub
		adviceMapper.insert(content,username,new Date());
	}

}
