package com.chen.nsfw.role.dao.impl;


import org.hibernate.Query;

import com.chen.core.dao.impl.BaseDaoImpl;
import com.chen.nsfw.role.dao.RoleDao;
import com.chen.nsfw.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{

	public void deleteRolePrivilegeByRoleId(String roleId) {
		
		Query query = getSession().createQuery("DELETE FROM RolePrivilege where id.role.roleId = ?");
		query.setParameter(0, roleId);
		query.executeUpdate();
		
	}

	
}
