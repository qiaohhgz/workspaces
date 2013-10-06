package com.demo.layout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo extends JFrame {
	private static final long serialVersionUID = -8883561601255707264L;

	public BorderLayoutDemo() {
		setLayout(new BorderLayout());
		add(new JButton("North"), BorderLayout.NORTH);
		add(new JButton("South"), BorderLayout.SOUTH);
		add(new JButton("East"), BorderLayout.EAST);
		add(new JButton("West"), BorderLayout.WEST);
		add(new JButton("Center"), BorderLayout.CENTER);

        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            ((JButton)component).setMargin(new Insets(10,10,10,10));
        }

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(400, 200, 400, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BorderLayoutDemo();
	}
}
