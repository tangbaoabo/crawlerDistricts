package com.tangbaobao.crawlercity;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author tangxuejun
 * @version 2019-03-27 22:06
 */
public class TestMy {

    @Test
    public void fun1() {
        Queue<Integer> list = new LinkedList<>(Arrays.asList(1, 2, 3, 4));

        while (list.size() > 0) {
            System.out.println("乖乖" + list.poll());
        }

    }
}
