/*
 * ChatServerFrame.java
 *
 * Created on __DATE__, __TIME__
 */

package com.ui.chat.frame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.ui.chat.panel.ChatServerPanel;

/**
 * @author jim_qiao
 * 
 */
public class ChatServerFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 6483828241351745121L;

	/** Creates new form ChatServerFrame */
	public ChatServerFrame() {
		initComponents();
		final ChatServerPanel cs = new ChatServerPanel();
		setTitle("Chat Server 8888");
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cs.disConnect();
				System.exit(0);
			}
		});
		cs.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ChatServerFrame().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	// End of variables declaration//GEN-END:variables

}