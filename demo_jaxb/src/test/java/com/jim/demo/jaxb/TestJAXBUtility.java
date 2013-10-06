package com.jim.demo.jaxb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import com.jim.demo.jaxb.user.User;
import com.jim.demo.jaxb.user.Users;

public class TestJAXBUtility {
	SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
	final String[] siteName = new String[] { "www.Allen White", "www.Ali White", "www.Amy White", "www.Arnold White", "www.Electra Black",
			"www.Evan Smith", "www.Field Smith", "www.Gallacher Jones", "www.Gallacher Jones", "www.Harold Williams", "www.Brewster Williams",
			"www.North Taylor", "www.Silas	Taylor", "http.Cecillia Brown", "http.Zangwill Brown", "www.Strong Davies", "http.Sara Davies",
			"http.Patience Evans", "www.Brown Evans", "www.Wilhelmina Wilson", "http.Nancy Wilson", "www.Oscar Thomas", "http.Veblen Thomas",
			"http.Pigou Johnson", "www.Electra Johnson", "http.Young Roberts", "http.Wright Roberts", "www.Milne Robinson", "http.Walkley Robinson",
			"http.Warner Thompson", "www.Kipling Thompson", "http.Defoe Wright", "http.Hill Wright", "www.Doherty Walker", "http.Keppel Walker",
			"http.Hood Edwards", "com.Yeates Edwards", "com.Dewey Hughes", "com.Dillon Hughes", "com.Joseph Green", "com.Copperfield Green",
			"com.Lambert Hall", "com.Tyler Hall", "com.Collins Lewis", "com.Jimmy Lewis", "com.Lincoln Harris", "com.Irving Harris",
			"com.Jackson Clarke", "com.Jordan Clarke", "com.Kellogga Patel" };

	@Test
	public void testUser() throws FileNotFoundException, JAXBException {
		User u = new User();
		u.setId(1000);
		u.setPassword("test123");
		u.setUserName("jim");
		u.setBirthday(format.format(new Date()));
		JAXBUtility.marshal(User.class, u, "demodata/User_2013.xml");
	}

	@Test
	public void testUsers() throws FileNotFoundException, JAXBException {
		String fileName = "demodata/User_";
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String yearStr = ("000" + year).substring(("000" + year).length() - 4);

		for (int j = 1; j <= 12; j++) {
			Users us = new Users();
			for (int i = 0; i < 31; i++) {
				User u = new User();
				u.setId(1000 + i + j);
				u.setPassword("test123");
				u.setUserName("jim");
				u.setBirthday(format.format(new Date()));
				us.getUser().add(u);
			}
			String monthStr = ("0" + j).substring(("0" + j).length() - 2);
			String formatFileName = MessageFormat.format("{0}{1}{2}.xml", fileName, yearStr, monthStr);
			JAXBUtility.marshal(Users.class, us, formatFileName);
		}
	}

	@Test
	public void testUnmarshal() throws FileNotFoundException, JAXBException {
		String fileName = "demodata/User_";
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		String yearStr = ("000" + year).substring(("000" + year).length() - 4);
		String formatFileName = MessageFormat.format("{0}{1}01.xml", fileName, yearStr);

		Users users = JAXBUtility.unmarshal(Users.class, formatFileName);
		Assert.assertNotNull(users);
		Assert.assertTrue(users.getUser().size() > 0);
	}

	@Test
	public void testToString() throws JAXBException, IOException {
		User u = new User();
		u.setId(1000);
		u.setPassword("test123");
		u.setUserName("jim");
		u.setBirthday(format.format(new Date()));
		String xml = JAXBUtility.toString(User.class, u);
		Assert.assertNotNull(xml);
	}

	@Test
	public void testToObject() throws JAXBException, IOException {
		User u = new User();
		u.setId(1000);
		u.setPassword("test123");
		u.setUserName("jim");
		u.setBirthday(format.format(new Date()));
		String xml = JAXBUtility.toString(User.class, u);
		Assert.assertNotNull(xml);
	}
}
