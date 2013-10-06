package com.demo.layout;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutDemo extends JFrame {
    private static final long serialVersionUID = -8883561601255707264L;
    JPanel panel;
    JPanel lPanel;
    JPanel rPanel;
    BoxLayout boxYLayout;
    BoxLayout lXLayout;
    BoxLayout rXLayout;

    public BoxLayoutDemo() {
        panel = new JPanel();
        lPanel = new JPanel();
        rPanel = new JPanel();

        boxYLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        lXLayout = new BoxLayout(lPanel, BoxLayout.X_AXIS);
        rXLayout = new BoxLayout(rPanel, BoxLayout.X_AXIS);

        panel.setLayout(boxYLayout);
        lPanel.setLayout(lXLayout);
        rPanel.setLayout(rXLayout);


        panel.setBackground(Color.yellow);
        panel.add(lPanel);
        panel.add(rPanel);

        lPanel.setBackground(Color.green);
        lPanel.add(new JButton("button1"));
        lPanel.add(new JButton("button2"));
        lPanel.add(new JButton("button3"));

        rPanel.setBackground(Color.blue);
        rPanel.add(new JButton("button4"));
        rPanel.add(new JButton("button5"));
        rPanel.add(new JButton("button6"));


        setLayout(new BorderLayout());

        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 200, 400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BoxLayoutDemo();
    }
}
