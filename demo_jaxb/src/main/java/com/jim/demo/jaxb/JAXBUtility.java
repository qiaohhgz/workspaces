package com.jim.demo.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Hello world!
 * 
 */
public class JAXBUtility {
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static boolean isOutputConsole = false;

	public static final <T> String marshal(Class<T> classes, T obj, String path) throws JAXBException, FileNotFoundException {
		StringWriter out = new StringWriter();
		JAXBContext ctx = JAXBContext.newInstance(classes);
		Marshaller m = ctx.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
		if (isOutputConsole)
			m.marshal(obj, System.out);// output to console
		FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
		m.marshal(obj, fileOutputStream);// output to file
		m.marshal(obj, out);// set to variable
		return out.toString();
	}

	public static final String getFileNameByCurrentDate() {
		String fileName = "demodata/User_";
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String formatFileName = MessageFormat.format("{0}{1}{2}{3}.xml", fileName, hour, minute, second);
		return formatFileName;
	}

	@SuppressWarnings("unchecked")
	public static final <T> T unmarshal(Class<T> classes, String path) throws JAXBException, FileNotFoundException {
		JAXBContext ctx = JAXBContext.newInstance(classes);
		Unmarshaller um = ctx.createUnmarshaller();
		FileInputStream fileInputStream = new FileInputStream(new File(path));
		Object obj = um.unmarshal(fileInputStream);
		return (T) obj;
	}

	public static final String toString(Class<?> c, Object o) throws JAXBException, IOException {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_ENCODING, DEFAULT_ENCODING);
			m.marshal(o, writer);
			StringBuffer s = writer.getBuffer();
			return s.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			writer.close();
		}
	}

	public static final Object toObject(Class<?> c, String xmlStr) throws JAXBException {
		StringReader reader = new StringReader(xmlStr);
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Object data = unmarshaller.unmarshal(reader);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			reader.close();
		}
	}
}
