package com.corejava.gt.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/15
 * @since v1.0
 */
public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

}
