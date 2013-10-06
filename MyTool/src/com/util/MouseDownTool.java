package com.util;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class MouseDownTool implements Runnable {
	private static Robot robot;
	private int time;
	private int addPointSize = 10;

	public MouseDownTool(int time) {
		this.time = time;
	}

	public void run() {
		try {
			robot = new Robot();
			while (true) {
				Thread.sleep(time);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getAddPointSize() {
		return addPointSize;
	}

	public void setAddPointSize(int addPointSize) {
		this.addPointSize = addPointSize;
	}
}
