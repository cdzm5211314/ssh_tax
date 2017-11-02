package com.chen.nsfw.role.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 * @ClassName: Role
 * @Description: 角色实体类 
 * @Author: ChenD
 * @CreateDate: 2017-11-2 上午10:05:44
 */
public class Role implements Serializable{
	
	private String roleId;	//角色ID
	private String name;	//角色名称
	private String state;	//角色状态
	
	private Set<RolePrivilege> rolePrivileges;
	
	//角色状态
	public static String ROLE_STATE_VALID = "1";// 有效
	public static String ROLE_STATE_INVALID = "0";// 无效
	
	public Role() {
	}

	public Role(String roleId, String name, String state, Set<RolePrivilege> rolePrivileges) {
		this.roleId = roleId;
		this.name = name;
		this.state = state;
		this.rolePrivileges = rolePrivileges;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}

	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}

}
