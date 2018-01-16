package com.chatbook.gt.corejava.swing;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/15
 * @since v1.0
 */
public class BallComponent extends JPanel {

    private static final int DEFAULT_WIDTH = 450;

    private static final int DEFAULT_HEIGHT = 350;

    private List<Ball> balls = new ArrayList<>();

    public void add(Ball b) {
        balls.add(b);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Ball b : balls) {
            g2.fill(b.getShape());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}
