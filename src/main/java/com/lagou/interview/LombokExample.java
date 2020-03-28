package com.lagou.interview;

import com.lagou.interview.entity.Person;

public class LombokExample {
    public static void main(String[] args) {
        Person person = new Person();
        person.setId(18);
        person.setName("Java");
        System.out.println(person.getName());
    }
}
