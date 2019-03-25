package com.github.qia.service;

import com.github.qia.pojo.User;

public interface UserService {

	User findOne(String name);

	void add(User user) throws Exception;

}
