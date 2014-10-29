package com.bzh.gt.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2014/10/7.
 */
public class Regex {

    @Test
    public void RegexTest() {
        String str = "B1121别志华";
        System.out.println(str.matches("[a-bA-B]\\d{4}[\\u4e00-\\u9fa5]{2,}"));
    }

    @Test
    public  void test2() {
        String str = "31#504-1";
        System.out.println(str.matches("\\d{2}#\\d{3}-\\d"));
    }
}
