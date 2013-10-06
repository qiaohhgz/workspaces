package com.demo.layout;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: JimQiao
 * Date: 4/2/13
 * Time: 12:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CenterLayoutDemo extends JFrame {
    GridBagConstraints c;
    JPanel panel;
    JLabel lbl;

    public CenterLayoutDemo() {
        init();
        initPanel();
        initComponent();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 200, 400, 300);
        setVisible(true);
    }

    protected void makeButton(String name, GridBagLayout layout, GridBagConstraints c) {
        Button button = new Button(name);
        layout.setConstraints(button, c);
        add(button);
    }


    public void init() {
        setLayout(new GridBagLayout());
    }

    public void initPanel() {
        panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        panel.setBackground(Color.ORANGE);

        lbl = new JLabel("休息下喝口水");
        c = new GridBagConstraints();
        panel.add(lbl, c);

        c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridheight = 2;
        c.weighty = 1.0;
        layout.setConstraints(panel, c);
        add(panel, c);
    }

    public void initComponent() {

    }

    public static void main(String[] args) {
        new CenterLayoutDemo();
    }
}
