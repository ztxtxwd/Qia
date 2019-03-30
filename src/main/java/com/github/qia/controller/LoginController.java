package com.github.qia.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.github.qia.pojo.User;
import com.github.qia.service.UserService;
import com.github.qia.util.CookieUtil;

import entity.Result;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping("/signin")
	public Result signin(@RequestBody User user){
		if(userService.findOne(user.getUsername())==null){
			return new Result(false, "请填写正确的用户名和密码");
		}
		if (!(user.getUsername()!=null&&user.getUsername()!=""&&user.getPassword()!=null&&user.getPassword()!="")) {
			return new Result(false, "请填写完整的用户名和密码");
		}
		User userByName=userService.findOne(user.getUsername());
		if (BCrypt.checkpw(user.getPassword(),userByName.getPassword())) {
			/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			request.setAttribute("userId", userByName.getId());
			request.setAttribute("userName", userByName.getUsername());*/
			CookieUtil.setCookie(request, response, "username", user.getUsername(), 3600*24, "UTF-8");
			//CookieUtil.setCookie(request, response, "pwd", user.getPassword(), 3600*24, "UTF-8");
			return new Result(true, userByName.getId()+"");
		}else{
			return new Result(false, "登录失败");
		}
	}
	
	@RequestMapping("/signout")
	public Result signout(){
		try {
			CookieUtil.deleteCookie(request, response, "username");
			//CookieUtil.deleteCookie(request, response, "pwd");
			return new Result(true, "注销成功");
			  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "注销失败");
		}
	}
}
