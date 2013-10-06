package com.util;

import java.awt.MouseInfo;
import java.awt.Point;

public class MousePointTool implements Runnable {
	private static Point p;
	private boolean started = true;
	private IMouseMoveAction action;

	public MousePointTool(IMouseMoveAction mouseMoveAction) {
		this.action = mouseMoveAction;
	}

	public void run() {
		try {
			while (started) {
				Thread.sleep(50);
				p = MouseInfo.getPointerInfo().getLocation();
				action.reload(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		started = true;
	}

	public void stop() {
		started = false;
	}

	public interface IMouseMoveAction {
		public void reload(Point p);
	}

}
