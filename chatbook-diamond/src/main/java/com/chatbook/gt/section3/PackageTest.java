package com.chatbook.gt.section3;

import org.junit.Test;

/**
 * 描述：深度克隆与浅度克隆
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class PackageTest {

    @Test
    public void testPackage() {
        Package p1 = new Package("Dior", new Color("red"));
        p1.print();

        Package p2 = p1.clone();
        p2.setName("0007");
        p2.getColor().setName("white");

        p1.print();
        p2.print();
    }

}
