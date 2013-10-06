package com.jim.junit.test;

import org.junit.Before;
import org.junit.Test;

import com.jim.junit.HelloWorld;

import static org.junit.Assert.*;

public class TestHelloWorld {

	public HelloWorld hw;

	@Before
	public void setUp() {
		hw = new HelloWorld();
	}

	@Test
	public void testHello() {
		String hello = hw.hello();
		assertEquals("not equals 'hello'", "hello", hello);
	}

	@Test
	public void testWorld() {
		String world = hw.world();
		assertEquals("not equals world", "world", world);
	}
}
