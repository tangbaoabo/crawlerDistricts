package com.tangbaobao.crawlercity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-03-28 10:03
 */
@AllArgsConstructor
@Getter
@ToString
public class Person {
    private String name;
    private int age;
    private List<Person> personList;
}
