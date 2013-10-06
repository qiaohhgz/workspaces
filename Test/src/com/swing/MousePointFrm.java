/*
 * Test.java
 *
 * Created on __DATE__, __TIME__
 */

package com.swing;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.util.RMousePoint;

/**
 * 
 * @author __USER__
 */
public class MousePointFrm extends javax.swing.JFrame {

	private static MousePointFrm thisFrm;

	/** Creates new form Test */
	private MousePointFrm(Point p) {
		initComponents();
		reLoad(p);
	}

	public void reLoad(Point p) {
		this.setLocation(getNewLocation(p));
		this.lblShowPoint.setText(getShowPointText(p));
	}

	public Point getNewLocation(Point p) {
		Point pointNew = p;
		int x = (int) p.getX();
		int y = (int) p.getY();
		int size = 10;
		int width = this.getWidth();
		int height = this.getHeight();
		if(p.getX() >= 1024 / 2){
			x = x - width - size;
		}
		else{
			x = x + size;
		}
			
		if(p.getY() >= 768 / 2){
			y = y - height - size;
		}	
		else{
			y = y + size;
		}
		pointNew.setLocation(x,y);
		return pointNew;
	}

	public String getShowPointText(Point p) {
		StringBuilder strPoint = new StringBuilder();
		strPoint.append("X=");
		strPoint.append(p.getX());
		strPoint.append("  ");
		strPoint.append("Y=");
		strPoint.append(p.getY());
		return strPoint.toString();
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

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblShowPoint, javax.swing.GroupLayout.DEFAULT_SIZE, 109,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				lblShowPoint, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));

		lblShowPoint.getAccessibleContext().setAccessibleName("lblShowPoint");

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static MousePointFrm getInstance(Point p) {
		if (null == thisFrm) {
			thisFrm = new MousePointFrm(p);
			thisFrm.setVisible(true);
		}
		return thisFrm;
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Thread(new RMousePoint()).start();
			}
		});
	}
	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private java.awt.Label lblShowPoint;
	// End of variables declaration//GEN-END:variables

}