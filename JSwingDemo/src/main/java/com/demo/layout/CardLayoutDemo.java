package com.demo.layout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardLayoutDemo extends JFrame implements MouseListener {
    private static final long serialVersionUID = -8883561601255707264L;

    public CardLayoutDemo() {
        CardLayout cardLayout = new CardLayout(10, 5);
        setLayout(cardLayout);
        add("North", new JButton("North"));
        add("South", new JButton("South"));
        add("East", new JButton("East"));
        add("West", new JButton("West"));
        add("Center", new JButton("Center"));
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            component.addMouseListener(this);
            ((JButton)component).setMargin(new Insets(10,10,10,10));
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(400, 200, 400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CardLayoutDemo();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            this.getContentPane().remove(source);
            System.out.println("remove text = \"" + source.getText() + "\" button");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
