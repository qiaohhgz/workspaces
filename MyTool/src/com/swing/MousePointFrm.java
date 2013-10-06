/*
 * Test.java
 *
 * Created on __DATE__, __TIME__
 */

package com.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.text.MessageFormat;

import com.util.MousePointTool;
import com.util.MousePointTool.IMouseMoveAction;

/**
 * 
 * @author __USER__
 */
public class MousePointFrm extends javax.swing.JFrame {

	/** Creates new form Test */
	private MousePointTool mousePoint;

	public MousePointFrm() {
		initComponents();
		setAlwaysOnTop(true);
		mousePoint = new MousePointTool(new IMouseMoveAction() {
			@Override
			public void reload(Point p) {
				MousePointFrm.this.setLocation(getNewLocation(p));
				lblShowPoint.setText(getShowPointText(p));
			}
		});
	}

	public Point getNewLocation(Point p) {
		Point pointNew = p;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) p.getX();
		int y = (int) p.getY();
		int size = 10;
		int width = this.getWidth();
		int height = this.getHeight();
		if (p.getX() >= screenSize.width / 2) {
			x = x - width - size;
		} else {
			x = x + size;
		}

		if (p.getY() >= screenSize.height / 2) {
			y = y - height - size;
		} else {
			y = y + size;
		}
		pointNew.setLocation(x, y);
		return pointNew;
	}

	public String getShowPointText(Point p) {
		return MessageFormat.format("X={0} Y={1}", p.getX(), p.getY());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lblShowPoint = new java.awt.Label();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);

		lblShowPoint.setText("  ");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblShowPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblShowPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		lblShowPoint.getAccessibleContext().setAccessibleName("lblShowPoint");

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MousePointFrm().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Label lblShowPoint;

	// End of variables declaration//GEN-END:variables

	public void stop() {
		setVisible(false);
		mousePoint.stop();
	}

	public void start() {
		setVisible(true);
		mousePoint.start();
		new Thread(mousePoint).start();
	}
}