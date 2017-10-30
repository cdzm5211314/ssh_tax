package com.chen.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI2Excel {
	
	@Test
	public void testWrite03Excel() throws Exception {
		// 1,创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2,创建工作表,指定工作表名称
		HSSFSheet sheet = workbook.createSheet("test1");
		// 3,创建行,创建第四行
		HSSFRow row = sheet.createRow(3);
		// 4,创建单元格,创建第四行第四列
		HSSFCell cell = row.createCell(3);
		cell.setCellValue("hello word 1");
		// 输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("f:\\itcast\\测试1.xls");
		// 把Excel写出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Test
	public void testRead03Excel() throws Exception {

		FileInputStream inputStream = new FileInputStream("f:\\itcast\\测试1.xls");
		// 1,读取工作薄
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		// 2,读取工作表,读取第一个工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 3,读取行,读取第四行
		HSSFRow row = sheet.getRow(3);
		// 4,读取单元格,读取第四行第四列
		HSSFCell cell = row.getCell(3);
		System.err.println("第3行第3列单元格的内容是: " + cell.getStringCellValue());
		workbook.close();
		inputStream.close();
	}

	@Test
	public void testWrite07Excel() throws Exception {
		// 1,创建工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 2,创建工作表,指定工作表名称
		XSSFSheet sheet = workbook.createSheet("test2");
		// 3,创建行,创建第四行
		XSSFRow row = sheet.createRow(3);
		// 4,创建单元格,创建第四行第四列
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("hello word 2");
		// 输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("f:\\itcast\\测试2.xlsx");
		// 把Excel写出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Test
	public void testRead07Excel() throws Exception {

		FileInputStream inputStream = new FileInputStream("f:\\itcast\\测试2.xlsx");
		// 1,读取工作薄
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		// 2,读取工作表,读取第一个工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		// 3,读取行,读取第四行
		XSSFRow row = sheet.getRow(3);
		// 4,读取单元格,读取第四行第四列
		XSSFCell cell = row.getCell(3);
		System.err.println("第3行第3列单元格的内容是: " + cell.getStringCellValue());
		workbook.close();
		inputStream.close();
	}

	@Test
	public void testRead03And07Excel() throws Exception {
		
		String fileName = "f:\\itcast\\测试1.xls";
		if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) { // 使用正则判断是否为Excel文档
			boolean is03Excel = fileName.matches("^.+\\.(?i)(xls)$");
			FileInputStream inputStream = new FileInputStream(fileName);
			// 1,读取工作薄
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			// 2,读取工作表,读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3,读取行,读取第四行
			Row row = sheet.getRow(3);
			// 4,读取单元格,读取第四行第四列
			Cell cell = row.getCell(3);
			System.err.println("第3行第3列单元格的内容是: " + cell.getStringCellValue());
			workbook.close();
			inputStream.close();
		}
	}
	
	@Test
	public void testExcelStyle() throws Exception {
		// 1,创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1创建合并单元格对象,合并第三行的第三列和第五列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 2, 2, 4);
		//1.2 创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3 创建字体样式
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints((short)16);//设置字体大小
		//1.4 加载字体样式
		style.setFont(font);
		// 1.5 单元格背景
		//设置背景填充模式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置填充背景色
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//设置填充前景色
		style.setFillForegroundColor(HSSFColor.RED.index);
		
		// 2,创建工作表,指定工作表名称
		HSSFSheet sheet = workbook.createSheet("Test");
		//2.1 加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		
		// 3,创建行,创建第四行
		HSSFRow row = sheet.createRow(2);
		
		// 4,创建单元格,创建第四行第四列
		HSSFCell cell = row.createCell(2);
		//4.1加载单元格样式
		cell.setCellStyle(style);
		cell.setCellValue("HelloWord");
		
		// 输出到硬盘
		FileOutputStream outputStream = new FileOutputStream("f:\\itcast\\测试.xls");
		// 把Excel写出到具体的地址
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
