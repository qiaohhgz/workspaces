package com.jim.junit.test;

import org.junit.Test;

import com.jim.junit.HelloWorld;
import static org.junit.Assert.*;

public class TestWorld {
	public HelloWorld hw = new HelloWorld();

	@Test
	public void test1() {
		assertEquals(hw.hello(), "hello");
	}
}
