package com.dtyunxi.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dtyunxi.app.dao.UserDao;
import com.dtyunxi.app.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDao userDao;
	@RequestMapping("getUser")
	public String login(String userName){
		Map<String,String> sqlMap = new HashMap<String,String>();
		sqlMap.put("userName",userName);
		
		User user = userDao.getUserInfo(sqlMap);
		return user.getName();
	}

}
