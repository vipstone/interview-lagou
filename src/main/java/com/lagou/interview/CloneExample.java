package com.lagou.interview;

/**
 * 克隆相关示例
 */
public class CloneExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 创建被赋值对象
        People p1 = new People();
        p1.setId(1);
        p1.setName("Java");
        // 克隆 p1 对象
        People p2 = (People) p1.clone();
        // 打印名称
        System.out.println("p2:" + p2.getName());

        // 分隔
        p2.setName("Lang");
        System.out.println(p1.getName());
        System.out.println("p2:" + p2.getName());
    }

    static class People implements Cloneable {
        private Integer id;
        private String name;

        /**
         * 重写 clone 方法
         * @throws CloneNotSupportedException
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
