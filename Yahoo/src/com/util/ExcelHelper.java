package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.db.model.Appointment;

public class ExcelHelper {
	public static final String FILE_TEMPLATE_PATH = "D:/JimQiao/Workspaces/Yahoo/src/Template.xlsx";
	public static final String BASEDIR = "";
	public static final String DATE_FORMAT_DAY = "d";
	public static final String DATE_FORMAT_WORK = "h:mm";
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFDataFormat dataFormat;
	private XSSFCellStyle dateCellStyle_day;
	private XSSFCellStyle dateCellStyle_work;
	private OutputStream os;

	public ExcelHelper(OutputStream os) {
		init();
		setDataFormat(getWorkbook().createDataFormat());
		dateCellStyle_day = getWorkbook().createCellStyle();
		dateCellStyle_work = getWorkbook().createCellStyle();
		dateCellStyle_day.setDataFormat(getDataFormat().getFormat(DATE_FORMAT_DAY));
		dateCellStyle_work.setDataFormat(getDataFormat().getFormat(DATE_FORMAT_WORK));
	}

	public void init() {
		try {
			setWorkbook(openWorkbook());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSheet(readSheet(null));
		setRow(readRow(getSheet(), DateHelper.getToDay() + 4));
		setFillCellColor();
	}

	public void setFillCellColor() {
		for (int r = 5; r < 36; r++) {
			XSSFRow row = getSheet().getRow(r);
			int day = DateHelper.getWeekDay(row.getCell(0).getDateCellValue());
			if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){
				for (int i = 0; i < 12; i++) {
					row.getCell(i).getCellStyle().setFillBackgroundColor(IndexedColors.BLUE.index);
					row.getCell(i).getCellStyle().setFillForegroundColor(IndexedColors.BLUE.index);
					row.getCell(i).getCellStyle().setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				}
			}
		}
	}

	public void writeSheet(Date date, Date beginWork, Date endWork, double workHours, Date beginOverTime,
			Date endOverTime, double overHours, String description) throws IOException {
		XSSFCell cell = null;
		int first = 0;
		if (date != null) {
			cell = readCell(first);
			cell.setCellValue(date);
		}
		if (beginWork != null) {
			cell = readCell(first + 1);
			cell.setCellValue(beginWork);
		}
		if (endWork != null) {
			cell = readCell(first + 2);
			cell.setCellValue(endWork);
		}
		if (workHours != -1) {
			cell = readCell(first + 3);
			cell.setCellValue(workHours);
		}
		if (beginOverTime != null) {
			cell = readCell(first + 5);
			cell.setCellValue(beginOverTime);
		}
		if (endOverTime != null) {
			cell = readCell(first + 6);
			cell.setCellValue(endOverTime);
		}
		if (overHours != -1) {
			cell = readCell(first + 7);
			cell.setCellValue(overHours);
		}
		if (description != null) {
			cell = readCell(first + 10);
			cell.setCellValue(cell.getStringCellValue() + description);
		}
		closeWorkbook(os);
	}

	public Appointment getAppointmentByRowId(int rowId) {
		if (getSheet().getRow(rowId) == null) {
			return null;
		}
		Appointment ap = new Appointment();
		XSSFRow row = readRow(getSheet(), rowId);
		XSSFCell cell = null;
		cell = row.getCell(1);
		if (cell.getBooleanCellValue()) {
			ap.setDay(cell.getDateCellValue());
		}
		cell = row.getCell(2);
		if (cell.getBooleanCellValue()) {
			ap.setBeginWorkTime(cell.getDateCellValue());
		}
		cell = row.getCell(3);
		if (cell.getBooleanCellValue()) {
			ap.setEndWorkTime(cell.getDateCellValue());
		}
		cell = row.getCell(4);
		if (cell.getBooleanCellValue()) {
			ap.setWorkHours((int) cell.getNumericCellValue());
		}
		cell = row.getCell(5);
		if (cell.getBooleanCellValue()) {
			ap.setBeginOverTime(cell.getDateCellValue());
		}
		cell = row.getCell(6);
		if (cell.getBooleanCellValue()) {
			ap.setEndOverTime(cell.getDateCellValue());
		}
		cell = row.getCell(7);
		if (cell.getBooleanCellValue()) {
			ap.setOverHours((int) cell.getNumericCellValue());
		}
		cell = row.getCell(8);
		if (cell.getBooleanCellValue()) {
			ap.setDescription(cell.getStringCellValue());
		}
		return ap;
	}

	public Date getBeginWorkTime() {
		return readCell(1).getDateCellValue();
	}

	public Date getBeginOverTime() {
		return readCell(5).getDateCellValue();
	}

	public void calcSumHours() {

	}

	public XSSFSheet readSheet(String sheetName) {
		if (sheetName == null || sheetName.trim().length() == 0) {
			return getWorkbook().getSheetAt(0);
		}
		return getWorkbook().getSheet(sheetName);
	}

	public XSSFRow readRow(XSSFSheet sheet, int day) {
		return sheet.getRow(day);
	}

	public XSSFCell readCell(int columnIndex) {
		return getRow().getCell(columnIndex);
	}

	public String getFilePath() {
		String fileName = DateHelper.FORMAT_YEAR_MONTH.format(DateHelper.getDate());
		String type = ".xlsx";
		return BASEDIR + fileName + type;
	}

	public XSSFWorkbook openWorkbook() throws IOException {
		String path = getFilePath();
		if (!new File(path).exists()) {
			File template = new File(FILE_TEMPLATE_PATH);
			if (!template.exists()) {
				throw new FileNotFoundException("Not Found:Excel template file.");
			}
			// read template excel file
			FileInputStream fis = new FileInputStream(new File(FILE_TEMPLATE_PATH));
			XSSFWorkbook work = new XSSFWorkbook(fis);
			fis.close();
			// new excel
			work.write(os);
			os.close();
		}
		FileInputStream is = null;
		is = new FileInputStream(new File(path));
		XSSFWorkbook work = new XSSFWorkbook(is);
		return work;
	}

	public void closeWorkbook(OutputStream os) throws IOException {
		getWorkbook().write(os);
		os.close();
	}

	public XSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public XSSFRow getRow() {
		return row;
	}

	public void setRow(XSSFRow row) {
		this.row = row;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public XSSFDataFormat getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(XSSFDataFormat dataFormat) {
		this.dataFormat = dataFormat;
	}
}
