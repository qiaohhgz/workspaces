package com.util;

import java.awt.MouseInfo;
import java.awt.Point;

import com.swing.MousePointFrm;

public class RMousePoint implements Runnable{
	private static MousePointFrm frm;
	private static Point p;

	public void run() {
		try {
			Thread.sleep(1000);
			p = MouseInfo.getPointerInfo().getLocation();
			frm = MousePointFrm.getInstance(p);
			while (true) {
				Thread.sleep(50);
				p = MouseInfo.getPointerInfo().getLocation();
				frm.reLoad(p);
				MouseInfo.getPointerInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
