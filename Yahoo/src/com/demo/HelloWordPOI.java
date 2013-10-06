package com.demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HelloWordPOI {
	public static void main(String[] args) {
		XSSFWorkbook work = null;
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		File file = null;
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
//			file = new File("a.xlsx");
//				os = new FileOutputStream(file);
//				work = new XSSFWorkbook();
//				work.createSheet("08");
//				work.write(os);
//				os.close();
//				
//			is = new FileInputStream(file);				
//			work = new XSSFWorkbook(is);
//			sheet = work.getSheetAt(0);
//			row = sheet.createRow(10);
//			cell = row.createCell(10);
//			cell.setCellValue("HelloWord");
//			cell = row.createCell(11);
//			
//			XSSFCellStyle cellStyle = work.createCellStyle();
//			XSSFDataFormat dataFormat = work.createDataFormat();
//			
//			cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd hh:mm:ss"));
//			cell.setCellValue(new Date());
//			cell.setCellStyle(cellStyle);
//			
//			os = new FileOutputStream(file);
//			work.write(os);
//			is.close();
//			os.close();
//			
//			work = new XSSFWorkbook(new FileInputStream(new File("b.xlsx")));
//			Date value = work.getSheetAt(0).getRow(0).getCell(0).getDateCellValue();
//			System.out.println(value);
			
			new HelloWordPOI().writeXlsx("c.xlsx");
			System.out.println("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeXlsx(String filePath)throws IOException{  
	     //工作簿  
	     XSSFWorkbook wb=new XSSFWorkbook();  
	    //获得CreationHelper对象,这个应该是一个帮助类     
	     XSSFCreationHelper helper=wb.getCreationHelper();  
	     //创建sheet页  
	     XSSFSheet sheet=wb.createSheet("我的测试sheet");  
	     //设置sheet名称  
//	     hssfworkbook.setSheetName(0,"我的测试sheet");   
	     //取得第一行   
	     XSSFRow firstRow=sheet.createRow(0);   
	     //创建第一个单元格  
	     XSSFCell hssfcell_0=firstRow.createCell(0);   
	     //hssfcell_0.setEncoding(HSSFWorkbook.ENCODING_UTF_16);并处理乱码  
	     //对第一个单元格赋值   
	     hssfcell_0.setCellValue("名称");   
	     //创建第二个单元格   
	     XSSFCell hssfcell_1=firstRow.createCell(1);   
	     hssfcell_1.setCellValue("创建日期");   
	     //日期单元格格式处理  
	     XSSFCellStyle dateCellStyle=wb.createCellStyle();  
	     //m/d/yyh:mm 设置日期格式  
	     dateCellStyle.setDataFormat(wb.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
	     dateCellStyle=setFillBackgroundColors(dateCellStyle, IndexedColors.BLACK.getIndex(), IndexedColors.YELLOW.getIndex(), dateCellStyle.SOLID_FOREGROUND);  
	     //设置其他标题  
	     firstRow.createCell(2).setCellValue("用户");   
	     firstRow.createCell(3).setCellValue("备注");  
	     
	     sheet.setColumnWidth(1, 1000/3*25);
	     //写入所有内容行  
	     for (int rowInt = 1; rowInt < 10; rowInt++) {  
	        XSSFRow row =sheet.createRow(rowInt);  
	        XSSFCell cell=row.createCell(0);    
	        cell.setCellValue("刘伯恩");  
	        
	        cell=row.createCell(1);    
	        cell.setCellValue(Calendar.getInstance().getTime());
	        cell.setCellStyle(dateCellStyle);
	        
	        cell=row.createCell(2);    
	        cell.setCellValue(Calendar.getInstance().getTime());
	        cell.setCellStyle(dateCellStyle);  
	        
	        cell=row.createCell(3);    
	        cell.setCellValue("这里是备注信息");  
	          
	    }  
	     //输出 49.  
	     FileOutputStream fileoutputstream=new FileOutputStream(filePath);   
	     wb.write(fileoutputstream);  
	     fileoutputstream.close();  
	 }  
	
	public static XSSFCellStyle setFillBackgroundColors(XSSFCellStyle cellStyle,short bg,short fg,short fp){     
        cellStyle.setFillBackgroundColor(bg);     
        cellStyle.setFillForegroundColor(fg);     
        cellStyle.setFillPattern(fp);     
        return cellStyle;     
    } 
}
