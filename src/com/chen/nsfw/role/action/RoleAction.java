package com.chen.nsfw.role.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.chen.core.action.BaseAction;
import com.chen.core.constant.Constant;
import com.chen.nsfw.role.entity.Role;
import com.chen.nsfw.role.entity.RolePrivilege;
import com.chen.nsfw.role.entity.RolePrivilegeId;
import com.chen.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.config.entities.ActionConfig;

public class RoleAction extends BaseAction {

	// 注入roleService
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;

	// 接受添加页面的权限选项
	private String[] privilegeIds;

	// 列表页面
	public String listUI() {
		// 加载权限集合并保存
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		roleList = roleService.findObjects();
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载权限集合并保存
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> hashSet = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						hashSet.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(hashSet);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑(修改)页面
	public String editUI() {
		// 加载权限集合并保存
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			role = roleService.findObjectById(role.getRoleId());
			// 处理权限回显
			if (role.getRolePrivileges() != null) {
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				for (RolePrivilege rp : role.getRolePrivileges()) {
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}

	// 保存编辑(修改)
	public String edit() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> hashSet = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						hashSet.add(new RolePrivilege(new RolePrivilegeId(role, privilegeIds[i])));
					}
					role.setRolePrivileges(hashSet);
				}
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
