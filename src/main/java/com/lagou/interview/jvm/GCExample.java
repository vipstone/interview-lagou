package com.lagou.interview.jvm;

/**
 * 垃圾回收演示示例
 */
public class GCExample {
    public static void main(String[] args) {
        CustomOne one = new CustomOne();
        CustomTwo two = new CustomTwo();
        one.setCustomTwo(two);
        two.setCustomOne(one);
        one = null;
        two = null;
    }
}

class CustomOne {
    private CustomTwo two;

    public CustomTwo getCustomTwo() {
        return two;
    }

    public void setCustomTwo(CustomTwo two) {
        this.two = two;
    }
}

class CustomTwo {
    private CustomOne one;

    public CustomOne getCustomOne() {
        return one;
    }

    public void setCustomOne(CustomOne one) {
        this.one = one;
    }
}


