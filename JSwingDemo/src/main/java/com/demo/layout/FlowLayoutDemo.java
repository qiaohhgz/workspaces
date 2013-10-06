package com.demo.layout;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo extends JFrame {
	private static final long serialVersionUID = -8883561601255707264L;

	public FlowLayoutDemo() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));

		for (int i = 0; i < 10; i++) {
			add(new JButton("Button" + i));
		}

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(400, 200, 400, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new FlowLayoutDemo();
	}
}
