package com.github.qia.controller;

import javax.naming.ldap.PagedResultsControl;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.qia.pojo.Question;
import com.github.qia.pojogroup.FullQuestion;
import com.github.qia.service.QuestionService;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@RequestMapping("/add")
	public Result ask(@RequestBody Question question){
		if (question.getUid()==null){
			return new Result(false, "请重新登录");
		}
		if (!(question.getTitle()!=null&&question.getTitle()!=""&&question.getVideo()!=null&&question.getVideo()!=""&&question.getDetail()!=null&&question.getDetail()!="")) {
			return new Result(false, "请填写完整信息");
		}
		try {
			questionService.add(question);
			return new Result(true, "发布成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "发布失败");
		}
	}
	
	@RequestMapping("/findList")
	public PageResult findList(int pageNum,int pageSize){
		if (pageSize==-1) {
			return questionService.findAll();
		}else{
			return questionService.findList(pageNum,pageSize);
		}
		
	}
	
	@RequestMapping("/findOne")
	public FullQuestion findOne(Long id,int pageNum,int pageSize){
		return questionService.findOne(id, pageNum, pageSize);
	}
	
	
}
