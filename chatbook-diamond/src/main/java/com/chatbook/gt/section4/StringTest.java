package com.chatbook.gt.section4;

import org.junit.Test;

/**
 * 1、String提供了字符串常量池机制，其运行机理是：创建一个字符串时，首先检查池中是否有值相同的字符串对象，
 *   如果有则不需要创建直接从池中刚查找到的对象引用；如果没有则新建字符串对象，返回对象引用，并且将新创建的对象放入池中。
 *   通过new方法创建的String对象是不检查字符串池的，也不会把对象放入池中。上述原则只适用于通过直接量给String对象引用赋值的情况。
 * <p>
 * 2、在字符串内容不经常发生变化的业务场景优先使用String类。例如：常量声明、少量的字符串拼接操作等。
 *    如果有大量的字符串内容拼接，避免使用String与String之间的“+”操作，因为这样会产生大量无用的中间对象，
 *    耗费空间且执行效率低下（新建对象、回收对象花费大量时间）。
 * <p>
 * 3、在频繁进行字符串的运算（如拼接、替换、删除等），并且运行在多线程环境下，建议使用StringBuffer，
 *    例如XML解析、HTTP参数解析与封装。
 * 4、在频繁进行字符串的运算（如拼接、替换、删除等），并且运行在单线程环境下，建议使用StringBuilder，
 *    例如SQL语句拼装、JSON封装等。
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class StringTest {

    @Test
    public void testString() {
        String str1 = "123";
        String str2 = new String("123");
        String str3 = "12" + "3";
        String str4 = "1" + "2" + "3";

        System.out.println(str1 == str2); // false
        System.out.println(str1 == str3); // true
        System.out.println(str1 == str4); // true

        System.out.println(str2 == str2.substring(0));  // true
        System.out.println(str2 == str2.substring(1));  // false
    }

}
