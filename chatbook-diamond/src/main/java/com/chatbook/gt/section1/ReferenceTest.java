package com.chatbook.gt.section1;

import org.junit.Test;

/**
 * 描述：java只有按值传递，只不过看传递的是对象引用还是基本类型副本
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class ReferenceTest {

    private <T> void swap(Point<T> a, Point<T> b) {
        Point<T> temp = a;
        a = b;
        b = temp;
        System.out.println("In swap======pointA:" + a.toString() + ", PointB:" + b.toString());
    }

    @Test
    public void testReference() {
        Point<Integer> pointA = new Point<>(0, 0);
        Point<Integer> pointB = new Point<>(10, 10);
        swap(pointA, pointB);

        System.out.println("In main======pointA:" + pointA.toString() + ", PointB:" + pointB.toString());
    }

}
