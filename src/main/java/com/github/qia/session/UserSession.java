package com.github.qia.session;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UserSession implements Serializable{
	private static final long serialVersionUID = 9120765714832970813L;
    //id
    private Integer userId;
    //账号
    private String username;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
