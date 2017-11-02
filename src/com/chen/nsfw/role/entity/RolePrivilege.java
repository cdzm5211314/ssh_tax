package com.chen.nsfw.role.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName: RolePrivilege
 * @Description: 角色权限实体类 
 * @Author: ChenD
 * @CreateDate: 2017-11-2 上午10:48:42
 */
public class RolePrivilege implements Serializable {

	private RolePrivilegeId id;

	public RolePrivilege() {
	}

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}
	
}
