package com.chen.nsfw.role.dao;

import com.chen.core.dao.BaseDao;
import com.chen.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	
	//删除该角色的所有权限
	public void deleteRolePrivilegeByRoleId(String roleId);

	
}
