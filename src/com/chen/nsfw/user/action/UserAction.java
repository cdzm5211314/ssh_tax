package com.chen.nsfw.user.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.chen.core.action.BaseAction;
import com.chen.nsfw.user.entity.User;
import com.chen.nsfw.user.service.UserService;

public class UserAction extends BaseAction {

	// 注入service
	@Resource
	private UserService userService;

	private List<User> userList;
	private User user;
	

	// 获取头像(提供get/set方法)
	private File headImg;
	private String headImgContentType;
	private String headImgFileName;

	//上传Excel表(提供get/set方法)
	private File userExcel;
	private String userExcelContentType;
	private String userExcelFileName;
	
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
		try {
			if (user != null) {
				// 处理头像
				if (headImg != null) {
					// 1,保存头像到upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 复制文件
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					// 2,设置用户头像路径
					user.setHeadImg("user/" + fileName);
				}
				userService.save(user);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
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
		try {
			if (user != null) {
				if (headImg != null) {
					// 1,保存头像到upload/user
					// 获取保存路径的绝对地址
					String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
					String fileName = UUID.randomUUID().toString().replaceAll("-", "") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
					// 复制文件
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					// 2,设置用户头像路径
					user.setHeadImg("user/" + fileName);
				}
				userService.update(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (user != null && user.getId() != null) {
			userService.delete(user.getId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				userService.delete(id);
			}
		}
		return "list";
	}
	
	//导出用户列表:Excel
	public void exporExcel(){
		try {
			//一 、查询用户列表
			userList = userService.findObjects();
			//二、 POI导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			userService.exportExcel(userList,outputStream);
			if (outputStream != null) {
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//导入Excel表
	public String importExcel(){
		// 1,获取Excel文件
		if (userExcel != null) {
			// 是否是Excel表
			if (userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				// 2,导入Excel表
				userService.importExcel(userExcel,userExcelFileName);
			}
		}
		 return "list";
	}
	
	//添加帐号时校验唯一性
	public void verifyAccount(){
		try {
			//1,获取帐号
			if (user != null && StringUtils.isNotBlank(user.getAccount())) {
				//2,根据帐号到数据库中校验是否存在该帐号
				List<User> list = userService.findUserByAccountAndId(user.getId(),user.getAccount());
				String strResult = "true";
				if (list != null && list.size() > 0) {
					//说明该帐号已经存在
					strResult = "false";
				}
				//输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public File getUserExcel() {
		return userExcel;
	}

	public void setUserExcel(File userExcel) {
		this.userExcel = userExcel;
	}

	public String getUserExcelContentType() {
		return userExcelContentType;
	}

	public void setUserExcelContentType(String userExcelContentType) {
		this.userExcelContentType = userExcelContentType;
	}

	public String getUserExcelFileName() {
		return userExcelFileName;
	}

	public void setUserExcelFileName(String userExcelFileName) {
		this.userExcelFileName = userExcelFileName;
	}

}
