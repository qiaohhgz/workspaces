package com.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.db.dao.AppointmentDao;
import com.db.model.Appointment;

public class IncludeExcel {
	private InputStream is;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private List<Appointment> list = new ArrayList<Appointment>();
	private final int[] cells = { 0, 1, 2, 3, 5, 6, 7, 10 };

	public IncludeExcel(InputStream is) {
		this.is = is;
		this.workbook = getWorkBook(is);
		this.sheet = workbook.getSheetAt(0);
		for (int rownum = 5; rownum < 36; rownum++) {
			row = sheet.getRow(rownum);
			Appointment ap = new Appointment();
			XSSFCell cell = null;

			cell = row.getCell(cells[0]);
			ap.setDay(cell.getDateCellValue());
			cell = row.getCell(cells[1]);
			ap.setBeginWorkTime(cell.getDateCellValue());
			cell = row.getCell(cells[2]);
			ap.setEndWorkTime(cell.getDateCellValue());
			cell = row.getCell(cells[3]);
			ap.setWorkHours((int) cell.getNumericCellValue());
			cell = row.getCell(cells[4]);
			ap.setBeginOverTime(cell.getDateCellValue());
			cell = row.getCell(cells[5]);
			ap.setEndOverTime(cell.getDateCellValue());
			cell = row.getCell(cells[6]);
			ap.setOverHours((int) cell.getNumericCellValue());
			cell = row.getCell(cells[7]);
			ap.setDescription(cell.getStringCellValue());
			list.add(ap);
		}
	}

	private XSSFWorkbook getWorkBook(InputStream is) {
		try {
			return new XSSFWorkbook(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

	public List<Appointment> getList() {
		return list;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String fileName = "Template.xlsx";
		InputStream is = IncludeExcel.class.getClassLoader().getResourceAsStream(fileName);
		List<Appointment> l = new IncludeExcel(is).getList();
		System.out.println(l.size());

		for (Appointment ap : l) {
			new AppointmentDao().save(ap);
		}
	}
}
