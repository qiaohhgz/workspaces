package com.util;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class RDeskTopLock implements Runnable {
	private static Robot robot;
	private static Point p;
	private int time;
	private int delay = 1000;
	private int addPointSize = 10;

	public RDeskTopLock(int time) {
		this.time = time;
	}

	public void run() {
		try {
			robot = new Robot();
			p = MouseInfo.getPointerInfo().getLocation();
			while (true) {
				Thread.sleep(time);
				p = MouseInfo.getPointerInfo().getLocation();
				robot.mouseMove(addPointSize + (int) p.getX(), (int) p.getY());
				robot.delay(delay);
				robot.mouseMove((int) p.getX(), (int) p.getY());
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

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getAddPointSize() {
		return addPointSize;
	}

	public void setAddPointSize(int addPointSize) {
		this.addPointSize = addPointSize;
	}
}
