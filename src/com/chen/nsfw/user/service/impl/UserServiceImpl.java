package com.chen.nsfw.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

	public void importExcel(File userExcel, String userExcelFileName) {
		try {
			//得到一个输入流
			FileInputStream inputStream = new FileInputStream(userExcel);
			//判断是excel表是03版本还是07之后版本
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			// 1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			// 2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3、读取行
			//判断excel表中行数是否超过2行,防止空行
			if (sheet.getPhysicalNumberOfRows() > 2) {
				User user = null;
				for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
					// 4、读取单元格
					Row row = sheet.getRow(i);
					user = new User();
					//获取每行中的每列数据
					//用户名
					Cell cell0 = row .getCell(0);
					user.setName(cell0.getStringCellValue());
					//帐号
					Cell cell1 = row .getCell(1);
					user.setAccount(cell1.getStringCellValue());
					//所属部门
					Cell cell2 = row .getCell(2);
					user.setDept(cell2.getStringCellValue());
					//性别
					Cell cell3 = row .getCell(3);
					user.setGender(cell3.getStringCellValue().equals("男"));
					//手机号--->需要处理数字
					Cell cell4 = row .getCell(4);
					String mobile = "";
					try {
						mobile = cell4.getStringCellValue();
					} catch (Exception e) {
						double dMobile = cell4.getNumericCellValue();
						mobile = BigDecimal.valueOf(dMobile).toString();
						e.printStackTrace();
					}
					user.setMobile(mobile);
					//电子邮箱
					Cell cell5 = row .getCell(5);
					user.setEmail(cell5.getStringCellValue());
					//生日
					Cell cell6 = row .getCell(6);
					if (cell6.getDateCellValue() != null) {
						user.setBirthday(cell6.getDateCellValue());
					}
					//默认用户密码为123456,
					user.setPassword("123456");
					//默认用户状态为有效
					user.setState(User.USER_STATE_VALID);
					// 5、保存用户
					save(user);
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
