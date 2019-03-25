package com.github.qia.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.qia.pojo.User;
import com.github.qia.service.UserService;

import entity.Result;

@RestController
@RequestMapping("/user")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/register.do")
	public Result register(@RequestBody User user){
		
		if (!(user.getUsername()!=null&&user.getUsername()!=""&&user.getPassword()!=null&&user.getPassword()!="")) {
			return new Result(false, "请填写完整的用户名和密码");
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		try {
			userService.add(user);
			return new Result(true, "注册成功！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false, "注册失败,用户名已被占用。");
		}
	}
}
