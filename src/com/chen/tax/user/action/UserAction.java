package com.chen.tax.user.action;

import java.util.List;

import javax.annotation.Resource;

import com.chen.tax.user.entity.User;
import com.chen.tax.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	
	//注入service
	@Resource
	private UserService userService;
	
	private List<User> userList;
	private User user;
	
	// 列表页面
	public String listUI() {
		userList = userService.findObjects();
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		return "addUI";
	}

	// 保存新增
	public String add() {
		if (user != null) {
			userService.save(user);
		}
		return listUI();
	}

	// 跳转到编辑(修改)页面
	public String editUI() {
		if (user != null && user.getId() != null) {
			user = userService.findObjectById(user.getId());
		}
		return "editUI";
	}

	// 保存编辑(修改)
	public String edit() {
		if (user != null) {
			userService.update(user);
		}
		return listUI();
	}

	// 删除
	public String delete() {
		if (user != null && user.getId() != null) {
			userService.delete(user.getId());
		}
		return listUI();
	}

	// 批量删除
	public String deleteSelected() {

		return listUI();
	}
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
