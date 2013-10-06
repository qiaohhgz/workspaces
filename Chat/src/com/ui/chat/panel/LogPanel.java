package com.ui.chat.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LogPanel extends JPanel {

	private static final long serialVersionUID = -7483697788503782716L;
	public static final String DEFAULT_PATTERN = "YYYY-MM-dd hh:mm:ss";
	private String pattern = DEFAULT_PATTERN;
	private SimpleDateFormat sdf;
	private JTextArea debugArea;

	public LogPanel() {
		sdf = new SimpleDateFormat(pattern);
		launchFrame();
	}

	public void launchFrame() {
		debugArea = new JTextArea(10, 30);
		setLayout(new BorderLayout());
		add(debugArea, BorderLayout.CENTER);
		debugArea.setEditable(false);
		setVisible(true);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		sdf = new SimpleDateFormat(pattern);
		this.pattern = pattern;
	}

	public void debug(String str) {
		debugArea.append(sdf.format(new Date()) + " " + str + "\n");
	}

	public void error(Exception ex) {
		debug(ex.getMessage());
	}
}
