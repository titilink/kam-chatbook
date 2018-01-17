package com.corejava.gt.swing;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * 描述：[描述]
 *
 * @author sandy
 * @date 2018/1/15
 * @since v1.0
 */
public class Ball {

    private static final int XSIZE = 15;

    private static final int YSIZE = 15;

    private double x = 0;

    private double y = 0;

    private double dx = 0;

    private double dy = 0;

    public void move(Rectangle2D bounds) {
        x += dx;
        y += dy;
        if ( x < bounds.getMinX() ) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if ( x + XSIZE >= bounds.getMaxX() ) {
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if ( y < bounds.getMinY() ) {
            y = bounds.getMinY();
            dy = - dy;
        }
        if ( y + YSIZE >= bounds.getMaxY() ) {
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }

}
