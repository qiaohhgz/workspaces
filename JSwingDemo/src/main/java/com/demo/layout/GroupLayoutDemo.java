package com.demo.layout;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GroupLayoutDemo extends JFrame {
	private static final long serialVersionUID = -8883561601255707264L;
	private JLabel label1;
	private JLabel label2;
	private JTextField tf1;
	private JTextField tf2;
	private JLabel lbl;
	private JTextField txt;
	private JButton button;
	private SequentialGroup vGroup;
	private ParallelGroup addComponent;
	private ParallelGroup addComponentlbl;
	private GroupLayout layout;
	private JPanel panel;

	public GroupLayoutDemo() {
		Container contentPane = getContentPane();
		label1 = new JLabel("user Name");
		label2 = new JLabel("password");
		tf1 = new JTextField();
		tf2 = new JTextField();

		lbl = new JLabel("passwor");
		txt = new JTextField();

		button = new JButton("new line");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel lbl = new JLabel("new lbl");
				addComponentlbl.addComponent(lbl);
				vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbl));
				panel.invalidate();
				pack();
			}
		});

		panel = new JPanel();
		layout = new GroupLayout(panel);
		panel.setLayout(layout);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		// Create a sequential group for the horizontal axis.

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		// The sequential group in turn contains two parallel groups.
		// One parallel group contains the labels, the other the text fields.
		// Putting the labels in a parallel group along the horizontal axis
		// positions them at the same x location.
		//
		// Variable indentation is used to reinforce the level of grouping.
		addComponentlbl = layout.createParallelGroup().addComponent(label1).addComponent(label2).addComponent(lbl);
		addComponent = layout.createParallelGroup().addComponent(tf1).addComponent(tf2).addComponent(txt)
				.addComponent(button);
		hGroup.addGroup(addComponentlbl);
		hGroup.addGroup(addComponent);

		layout.setHorizontalGroup(hGroup);

		// Create a sequential group for the vertical axis.
		vGroup = layout.createSequentialGroup();

		// The sequential group contains two parallel groups that align
		// the contents along the baseline.The first parallel group contains
		// the first label and text field, and the second parallel group
		// contains
		// the second label and text field.By using a sequential group
		// the labels and text fields are positioned vertically after one
		// another.
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label1).addComponent(tf1));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label2).addComponent(tf2));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbl).addComponent(txt));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(button));
		layout.setVerticalGroup(vGroup);

		contentPane.add(panel);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GroupLayoutDemo();
	}
}
