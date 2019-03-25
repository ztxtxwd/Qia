package com.github.qia.pojogroup;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.qia.pojo.Comment;
import com.github.qia.pojo.Question;

public class FullQuestion {
	private Question question;
	private Page<Comment> comments;
	private int total;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Page<Comment> getComments() {
		return comments;
	}
	public void setComments(Page<Comment> comments) {
		this.comments = comments;
	}
	
}
