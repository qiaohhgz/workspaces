package com.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.db.dao.BaseDao;
import com.db.model.Appointment;

public class OutputExcel {
	private final String[] headName = { "日期", "上班", "下班", "上班用时", "开始加班", "结束加班", "加班时间", "备注" };
	private final int[] cells = { 0, 1, 2, 3, 4, 5, 6, 7 };
	public static final SimpleDateFormat format_day = new SimpleDateFormat("d");
	public static final SimpleDateFormat format_mm_ss = new SimpleDateFormat("hh:mm");
	private OutputStream os;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private XSSFCellStyle cellStyle;

	public OutputExcel(OutputStream os, List<Appointment> list) throws IOException {
		this.os = os;
		this.workbook = new XSSFWorkbook();
		this.sheet = this.workbook.createSheet("Sheet1");
		int rowIndex = 0;
		cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		row = sheet.createRow(rowIndex++);
		for (int j = 0; j < cells.length; j++) {
			row.createCell(j).setCellValue(headName[j]);
		}
		for (Appointment ap : list) {
			System.out.println(rowIndex);
			row = sheet.createRow(rowIndex);
			createCell(cells[0]).setCellValue(Integer.parseInt(get(ap.getDay(), format_day)));
			createCell(cells[1]).setCellValue(get(ap.getBeginWorkTime(), format_mm_ss));
			createCell(cells[2]).setCellValue(get(ap.getEndWorkTime(), format_mm_ss));
			createCell(cells[3]).setCellValue(ap.getWorkHours());
			createCell(cells[4]).setCellValue(get(ap.getBeginOverTime(), format_mm_ss));
			createCell(cells[5]).setCellValue(get(ap.getEndOverTime(), format_mm_ss));
			createCell(cells[6]).setCellValue(ap.getOverHours());
			createCell(cells[7]).setCellValue(ap.getDescription());
			rowIndex++;
		}
		workbook.write(os);
		os.close();
	}

	public String get(Date date, DateFormat format) {
		if (date == null) {
			return null;
		}
		return format.format(date);
	}

	public XSSFCell createCell(int cellIndex) {
		cell = row.createCell(cellIndex);
		cell.setCellStyle(cellStyle);
		return cell;
	}

	public OutputStream getOs() {
		return os;
	}

	public void setOs(OutputStream os) {
		this.os = os;
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		new OutputExcel(new FileOutputStream(new File("a.xlsx")), BaseDao.getDB().getAppointments());
	}
}
