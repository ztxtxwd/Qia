package com.github.qia.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.qia.pojo.User;
import com.github.qia.service.UserService;

import entity.Result;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/signin")
	public Result signin(@RequestBody User user){
		if (!(user.getUsername()!=null&&user.getUsername()!=""&&user.getPassword()!=null&&user.getPassword()!="")) {
			return new Result(false, "请填写完整的用户名和密码");
		}
		User userByName=userService.findOne(user.getUsername());
		if (BCrypt.checkpw(user.getPassword(),userByName.getPassword())) {
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			request.setAttribute("userId", userByName.getId());
			request.setAttribute("userName", userByName.getUsername());

			return new Result(true, userByName.getId()+"");
		}else{
			return new Result(false, "登录失败");
		}
	}
}
