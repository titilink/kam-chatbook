package com.java8.gt.deeper;

import com.java8.gt.Apple;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：[描述]
 *
 * @author ganting
 * @date 2018-01-17
 * @since v1.0
 */
public class BehaviorParameter {

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple(151, "red"), new Apple(100, "green"));

        apples.sort((Apple a1, Apple a2) -> a1.getHeavy().compareTo(a2.getHeavy()));

        apples.sort((Apple a1, Apple a2) -> a1.getColor().compareTo(a2.getColor()));

        new Thread(() -> System.out.println("Hello")).start();

        Button button = new Button();
        button.addActionListener((ActionEvent e) -> System.out.println(button.getName()));
    }

}
