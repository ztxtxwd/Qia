package com.github.qia.service;

import javax.swing.text.AbstractDocument.Content;

import com.github.qia.pojo.Comment;

public interface CommentService {
	void add(Comment comment);

	void advise(String content, String username);
}
