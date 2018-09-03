package com.dtyunxi.app.dao;

import java.util.Map;

import com.dtyunxi.app.pojo.User;

public interface UserDao {

	User getUserInfo(Map<String, String> sqlMap);

}
