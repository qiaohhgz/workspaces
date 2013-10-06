/*
 * TableFrm.java
 *
 * Created on __DATE__, __TIME__
 */

package com.swing;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.util.AutoInputTool;
import com.util.MouseDownTool;

/**
 * 
 * @author __USER__
 */
public class ToolFrm extends javax.swing.JFrame {
	private final static Gson g = new Gson();
	private final static String PATH = "C:\\ProgramData\\MyTool\\config\\ToolFrm.json";
	private final static Logger log = Logger.getLogger(ToolFrm.class);

	/** Creates new form TableFrm */
	public ToolFrm() {
		initComponents();
		txtTime.setValue(1000 * 15);
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(cbxAlwayOnTop.isSelected());
		pointFrame = new MousePointFrm();
		loadConfig();
	}

	public void loadConfig() {
		File file = new File(PATH);
		try {
			if (!file.exists()) {
				return;
			}
			FileReader fr = new FileReader(file);
			Map<String, Object> attrs = g.fromJson(fr, Map.class);
			fr.close();
			txtTime.setValue(Double.parseDouble(attrs.get("time").toString()));
			tfLoginName.setText(attrs.get("loginName").toString().trim());
			nameKeyCodes = (List<KeyEvent>) attrs.get("nameKeyCodes");
			if(nameKeyCodes == null){
				nameKeyCodes = new ArrayList<KeyEvent>();
			}
			sx.setValue(Double.parseDouble(attrs.get("x").toString()));
			sy.setValue(Double.parseDouble(attrs.get("y").toString()));
			cbxAutoEnter.setSelected(Boolean.parseBoolean(attrs.get("autoEnter").toString()));
			cbxAlwayOnTop.setSelected(Boolean.parseBoolean(attrs.get("alwayOnTop").toString()));
		} catch (Exception e) {
			log.error(e);
			file.delete();
			e.printStackTrace();
		}
	}

	public void saveConfig() {
		File file = new File(PATH);
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("time", Double.parseDouble(txtTime.getValue().toString()));
			attrs.put("loginName", tfLoginName.getText().trim());
			attrs.put("nameKeyCodes", nameKeyCodes);
			attrs.put("x", Double.parseDouble(sx.getValue().toString()));
			attrs.put("y", Double.parseDouble(sy.getValue().toString()));
			attrs.put("autoEnter", cbxAutoEnter.isSelected());
			attrs.put("alwayOnTop", cbxAlwayOnTop.isSelected());
			String json = g.toJson(attrs, Map.class);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			byte[] bs = json.getBytes();
			fos.write(bs, 0, bs.length);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			log.error(e);
			file.delete();
			e.printStackTrace();
		}
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		btnStart = new javax.swing.JButton();
		txtTime = new javax.swing.JSpinner();
		jLabel1 = new javax.swing.JLabel();
		tfLoginName = new javax.swing.JTextField();
		pfPwd = new javax.swing.JPasswordField();
		btnInput = new javax.swing.JButton();
		cbxAutoEnter = new javax.swing.JCheckBox();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		btnClearLoginName = new javax.swing.JButton();
		btnClearPwd = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		sx = new javax.swing.JSpinner();
		sy = new javax.swing.JSpinner();
		btnMPT = new javax.swing.JButton();
		btnStopMPT = new javax.swing.JButton();
		cbxAlwayOnTop = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				formWindowClosing(evt);
			}
		});

		btnStart.setText("start");
		btnStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStartActionPerformed(evt);
			}
		});

		txtTime.setRequestFocusEnabled(false);

		jLabel1.setText("time:");

		tfLoginName.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				tfLoginNameKeyReleased(evt);
			}
		});

		pfPwd.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				pfPwdKeyReleased(evt);
			}
		});

		btnInput.setText("Input");
		btnInput.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnInputActionPerformed(evt);
			}
		});

		cbxAutoEnter.setSelected(true);
		cbxAutoEnter.setText("Auto Enter");

		jLabel2.setText("Login Name:");

		jLabel3.setText("Password:");

		btnClearLoginName.setText("Clear KeyCodes");
		btnClearLoginName.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearLoginNameActionPerformed(evt);
			}
		});

		btnClearPwd.setText("Clear KeyCodes");
		btnClearPwd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearPwdActionPerformed(evt);
			}
		});

		jLabel4.setText("X:");

		jLabel5.setText("Y:");

		btnMPT.setText("Start MousePoint Tool");
		btnMPT.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnMPTActionPerformed(evt);
			}
		});

		btnStopMPT.setText("Stop");
		btnStopMPT.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnStopMPTActionPerformed(evt);
			}
		});

		cbxAlwayOnTop.setText("Alway on top");
		cbxAlwayOnTop.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				cbxAlwayOnTopStateChanged(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(jLabel1)
																				.addComponent(jLabel2)
																				.addComponent(jLabel4)
																				.addComponent(jLabel5)
																				.addComponent(jLabel3))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														sx)
																												.addComponent(
																														sy,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														79,
																														Short.MAX_VALUE))
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										49,
																										Short.MAX_VALUE)
																								.addComponent(
																										cbxAutoEnter))
																				.addComponent(
																						pfPwd,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						209, Short.MAX_VALUE)
																				.addComponent(
																						txtTime,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						209, Short.MAX_VALUE)
																				.addComponent(
																						tfLoginName,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						209, Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						btnInput,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnClearPwd,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnStart,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						btnClearLoginName,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(cbxAlwayOnTop)
																.addGap(82, 82, 82)
																.addComponent(btnMPT)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(btnStopMPT,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 72,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(btnStart)
										.addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2)
										.addComponent(btnClearLoginName)
										.addComponent(tfLoginName, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(8, 8, 8)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3)
										.addComponent(pfPwd, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(btnClearPwd))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(sx, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel5)
										.addComponent(sy, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(cbxAutoEnter)
										.addComponent(btnInput))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(
								layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(cbxAlwayOnTop).addComponent(btnMPT).addComponent(btnStopMPT))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void formWindowClosing(java.awt.event.WindowEvent evt) {
		// TODO add your handling code here:
		saveConfig();
	}

	private void cbxAlwayOnTopStateChanged(javax.swing.event.ChangeEvent evt) {
		// TODO add your handling code here:
		setAlwaysOnTop(cbxAlwayOnTop.isSelected());
	}

	private void btnStopMPTActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		pointFrame.stop();
	}

	private MousePointFrm pointFrame;

	private void btnMPTActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		pointFrame.start();
	}

	private void btnClearPwdActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		pfPwd.setText("");
		pwdKeyCodes.clear();
	}

	private void btnClearLoginNameActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		tfLoginName.setText("");
		nameKeyCodes.clear();
	}

	private void btnInputActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		int x = Integer.parseInt(sx.getValue().toString());
		int y = Integer.parseInt(sy.getValue().toString());
		AutoInputTool autoInputTool = new AutoInputTool(nameKeyCodes, pwdKeyCodes, cbxAutoEnter.isSelected(),
				new Point(x, y));
		autoInputTool.start();
	}

	List<KeyEvent> nameKeyCodes = new ArrayList<KeyEvent>();
	List<KeyEvent> pwdKeyCodes = new ArrayList<KeyEvent>();

	private void tfLoginNameKeyReleased(java.awt.event.KeyEvent evt) {
		// TODO add your handling code here:
		log.debug(evt.getKeyCode());
		nameKeyCodes.add(evt);
	}

	private void pfPwdKeyReleased(java.awt.event.KeyEvent evt) {
		// TODO add your handling code here:
		log.debug(evt.getKeyCode());
		pwdKeyCodes.add(evt);
	}

	private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {
		int time = Integer.parseInt(txtTime.getValue().toString());
		MouseDownTool topLock = new MouseDownTool(time);
		new Thread(topLock).start();

		txtTime.setEnabled(false);
		btnStart.setEnabled(false);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ToolFrm().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton btnClearLoginName;
	private javax.swing.JButton btnClearPwd;
	private javax.swing.JButton btnInput;
	private javax.swing.JButton btnMPT;
	private javax.swing.JButton btnStart;
	private javax.swing.JButton btnStopMPT;
	private javax.swing.JCheckBox cbxAlwayOnTop;
	private javax.swing.JCheckBox cbxAutoEnter;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPasswordField pfPwd;
	private javax.swing.JSpinner sx;
	private javax.swing.JSpinner sy;
	private javax.swing.JTextField tfLoginName;
	private javax.swing.JSpinner txtTime;
	// End of variables declaration//GEN-END:variables

}
