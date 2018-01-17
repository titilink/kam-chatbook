package com.corejava.gt.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/15
 * @since v1.0
 */
public class BounceFrame extends JFrame {

    private BallComponent comp;

    public static final int STEPS = 1000;

    public static final int DELAY = 500;

    public BounceFrame() {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Stop", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton jButton = new JButton(title);
        c.add(jButton);
        jButton.addActionListener(listener);
    }

    public void addBall() {
        Runnable r = () -> {
            try {
                Ball ball = new Ball();
                comp.add(ball);

                for (int i = 0; i <= STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.repaint();

                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

}
