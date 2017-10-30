package com.chen.nsfw.user.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;


import org.springframework.stereotype.Service;

import com.chen.core.util.ExcelUtil;
import com.chen.nsfw.user.dao.UserDao;
import com.chen.nsfw.user.entity.User;
import com.chen.nsfw.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	// 注入dao
	@Resource
	private UserDao userDao;

	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(Serializable id) {
		userDao.delete(id);
	}

	public User findObjectById(Serializable id) {
		return userDao.findObjectById(id);
	}

	public List<User> findObjects() {
		return userDao.findObjects();
	}

	public void exportExcel(List<User> userList, ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(userList, outputStream);
	}

	
}
