package com.chatbook.gt.section5;

/**
 * 描述：NullPointException作为运行时异常（非受检异常），会导致当前线程退出，
 *       如果当前线程是main主线程，将导致程序退出。
 *
 * @author sandy
 * @date 2018/1/23
 * @since v1.0
 */
public class NullPointExceptionTest {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Object nullObj = null;
            try {
                Thread.sleep(5000);

                System.out.println(Thread.currentThread().getName() + " print");

                nullObj.hashCode(); // throw NullPointException
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setName("NullPointException-Thread");
        t.start();

        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " print " + i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
