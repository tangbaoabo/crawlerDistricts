package com.tangbaobao.crawlercity.traverse;

import com.tangbaobao.crawlercity.domain.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-03-28 10:02
 */
public class Deep {


    /**
     * 先获取所有省份，存入队列
     * 再依次获取每个省份的城市，然后存入队列
     */
    @Test
    public void fun1() {
        List<Person> people = List.of(new Person("tangbaobao", 112, List.of(
                new Person("tangbaobao1", 112, List.of(
                        new Person("tangbaobao1", 112, new ArrayList<>()))),
                new Person("tangbaobao2", 112, new ArrayList<>()))));
        for (int i = 0; i < people.size(); i++) {
            System.out.println();
        }


    }


}
