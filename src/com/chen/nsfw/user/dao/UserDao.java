package com.chen.nsfw.user.dao;

import java.util.List;

import com.chen.core.dao.BaseDao;
import com.chen.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User> {
	
	//根据用户id和用户帐号查询用户
	List<User> findUserByAccountAndId(String id, String account);

}
