package com.github.qia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;








import com.github.qia.mapper.UserMapper;
import com.github.qia.pojo.User;
import com.github.qia.pojo.UserExample;
import com.github.qia.pojo.UserExample.Criteria;
import com.github.qia.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findOne(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(name);
		try {
			List<User> list=userMapper.selectByExample(example);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("!!!");
			return null;
		}
		
		
	}

	@Override
	public void add(User user) throws Exception {
		// TODO Auto-generated method stub
		
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUsernameEqualTo(user.getUsername());
		List<User> list=userMapper.selectByExample(example);
		if(list.size()>0){
			throw new Exception("用户名已被占用");
		}
		userMapper.insert(user);
	}

}
