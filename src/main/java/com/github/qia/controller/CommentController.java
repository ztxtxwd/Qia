package com.github.qia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.qia.pojo.Comment;
import com.github.qia.service.CommentService;

import entity.Result;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/add")
	public Result add(@RequestBody Comment comment){
		if (comment.getContent()==null||comment.getContent()==""){
			return new Result(false, "回复内容不能为空");
		}
		if (comment.getUname()==null||comment.getUname()==""){
			return new Result(false, "请重新登录");
		}	
		
		try {
			commentService.add(comment);
			return new Result(true, "回复成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "回复失败");
		}
	}
	
	@RequestMapping("/advise")
	public Result advise(String content,String username){
		if (content==null){
			return new Result(false, "反馈内容不能为空");
		}
		if (username==null||username==""){
			return new Result(false, "请重新登录");
		}
		
		try {
			commentService.advise(content,username);
			return new Result(true, "感谢你的反馈");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "出现了一些问题，你的反馈没有被记录，依然谢谢你。");
		}
	}
}
