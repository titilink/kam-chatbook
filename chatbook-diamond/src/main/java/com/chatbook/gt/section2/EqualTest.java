package com.chatbook.gt.section2;

import org.junit.Test;

/**
 * 描述：[描述]
 *
 * @author ganting
 * @date 2018-01-16
 * @since v1.0
 */
public class EqualTest {

    @Test
    public void testEqual() {
        String str1 = "敲黑板";
        String str2 = "敲黑板";
        String str3 = new String("敲黑板");
        boolean  ret;

        ret = (str1 == str2); 		//true
        System.out.println(ret);
        ret = (str1.equals(str2));	//true
        System.out.println(ret);
        ret = (str1 == str3);    	//false
        System.out.println(ret);
        ret = (str1.equals(str3)); 	//true
        System.out.println(ret);
        ret = (str1 == str1.trim());    //true
        System.out.println(ret);
    }

}
