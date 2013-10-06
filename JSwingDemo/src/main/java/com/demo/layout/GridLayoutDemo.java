package com.demo.layout;

import javax.swing.*;
import java.awt.*;

public class GridLayoutDemo extends JFrame {
    private static final long serialVersionUID = -8883561601255707264L;

    public GridLayoutDemo() {
        GridLayout layout = new GridLayout(2, 4);
        setLayout(layout);
        for (int i = 0; i < 10; i++) {
            add(new JButton("button" + (i + 1)));
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 200, 400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutDemo();
    }
}
