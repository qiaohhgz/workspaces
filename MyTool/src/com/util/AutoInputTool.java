package com.util;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class AutoInputTool {
	private static Robot robot;
	private List<KeyEvent> nameKeyCodes;
	private List<KeyEvent> pwdKeyCodes;
	private boolean autoEnter;
	private Point point;

	public AutoInputTool(List<KeyEvent> nameKeyCodes, List<KeyEvent> pwdKeyCodes, boolean autoEnter, Point point) {
		this.nameKeyCodes = nameKeyCodes;
		this.pwdKeyCodes = pwdKeyCodes;
		this.point = point;
		this.autoEnter = autoEnter;
	}

	public void start() {
		try {
			robot = new Robot();
			
			robot.mouseMove((int) point.getX(), (int) point.getY());

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			inputText(nameKeyCodes);

			onClick(KeyEvent.VK_TAB);

			inputText(pwdKeyCodes);

			if (autoEnter) {
				onClick(KeyEvent.VK_ENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inputText(List<KeyEvent> keyCodes) {
		for (KeyEvent key : keyCodes) {
			if (key.isShiftDown()) {
				robot.keyPress(KeyEvent.VK_SHIFT);
			}
			if (key.isControlDown()) {
				robot.keyPress(KeyEvent.VK_CONTROL);
			}
			onClick(key.getKeyCode());
			if (key.isControlDown()) {
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			if (key.isShiftDown()) {
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
	}

	public void onClick(int keyCode) {
		robot.keyPress(keyCode);
		robot.keyRelease(keyCode);
	}
}
