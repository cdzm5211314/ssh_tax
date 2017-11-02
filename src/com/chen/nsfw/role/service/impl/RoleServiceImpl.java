package com.chen.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chen.nsfw.role.dao.RoleDao;
import com.chen.nsfw.role.entity.Role;
import com.chen.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	//注入RoleDao
	@Resource
	private RoleDao roleDao;
	
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		//删除该角色之前的所有权限
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//更新角色与权限
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}

}
