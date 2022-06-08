package com.ax.juc._13stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xing
 * @version 1.0.0
 * @ClassName StreamTests.java
 * @Description TODO
 * @createTime 2022年03月06日 14:40:00
 * https://juejin.cn/post/7064757819165114404
 */


class Person {
    private String name; // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area; // 地区

    // 构造方法
    public Person(String name, int salary, int age, String sex, String area) {
        this.name = name;
        this.salary = salary;
        this.age = age;
        this.sex = sex;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}


class StreamTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        // 匹配第一个
        Optional<Integer> findFirst = list.stream().filter(x -> x > 5).findFirst();
        System.out.println("匹配第一个值：" + findFirst.orElse(null));
//        System.out.println("匹配第一个值：" + findFirst.get());
        // 匹配任意（适用于并行流）,会随机返回一个
        Optional<Integer> findAny = list.parallelStream().filter(x -> x > 5).findAny();
        System.out.println("匹配任意一个值：" + findAny.orElse(null));

        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("是否存在大于6的值：" + anyMatch);
    }
}

class StreamTest_filter_map {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("高于8000的员工姓名：" + fiterList);
    }
}


class StreamTest_max {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");

        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串：" + max.get());
    }
}

class StreamTest_max_int {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);

        // 自然排序
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        System.out.println("自然排序的最大值：" + max.get());


        // 自定义排序
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("自定义排序的最大值：" + max2.get());
    }
}

class StreamTest_max_str {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        final Optional<Person> optionalPerson = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("员工工资最大值：" + optionalPerson.get().getSalary());

        final Optional<String> optional = personList.stream().max(Comparator.comparingInt(Person::getSalary)).map(Person::getName);
        System.out.println("员工工资最大值的姓名 = " + optional.orElse(null));
    }
}


class StreamTest_flatMap {
    // "Hello","World" 拆开每个字符集
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "World");
        List<String> listNew = list.stream().flatMap(s -> {
            // 将每个元素转换成一个stream
            String[] split = s.split("");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());

        System.out.println("处理前的集合：" + list);
        System.out.println("处理后的集合：" + listNew);
    }
}

//归约 减少 ，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
class StreamTest_reduce {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0, 1, 3, 2, 8, 11, 4);
        // 求和方式1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // 求和方式2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // 求和方式3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        final Integer sum4 = list.stream().collect(Collectors.summingInt(value -> value));

        System.out.println("list求和：" + sum.get() + "," + sum2.get() + "," + sum3 + " Collectors方式求和 sum4 = " + sum4);


        // 求乘积
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        System.out.println("list求积：" + product.get());

        // 求最大值方式1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);

        // Stream的错误使用：Stream.max(Integer::max)和Stream.min(Integer::min)
        Optional<Integer> max1 = list.stream().max(Integer::max);

//        Optional<Integer> max2 = list.stream().max(Integer::compare);
        Optional<Integer> max2 = list.stream().max(Comparator.naturalOrder());

        System.out.println("降序,查找最小 = " + list.stream().max(Comparator.reverseOrder()));

        // 求最大值写法2
        Integer max3 = list.stream().reduce(1, Integer::max);

        System.out.println("最大值：" + max.get() + ",max1错误用法 = " + max1.get() + ",max2 = " + max2 + ",max3 = " + max3);


        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6);


        System.out.println("最大值 max 正确用法,所以需要用排序方法：" + list1.stream().max((a, b) -> {
            if (a > b) {
                return 1;
            } else return -1;
        }).get());


        // 其实result返回的一直是两个值的最大值，而数据中全部都为正数，所以返回值总是正数。
        int a = Stream.of(2, 1, 4, 5, 3).max((v, k) -> {
            System.out.println("v = " + v);
            System.out.println("k = " + k);
            int result = Integer.max(v, k);
            System.out.println("result = " + result);
            return result;
        }).get();
        System.out.println(a);


    }
}

/*
* Collectors提供了一系列用于数据统计的静态方法：

计数：count
平均值：averagingInt、averagingLong、averagingDouble
最值：maxBy、minBy
求和：summingInt、summingLong、summingDouble
统计以上所有：summarizingInt、summarizingLong、summarizingDouble

作者：Levng
链接：https://juejin.cn/post/7064757819165114404
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*
* */
class StreamTest_count {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getSalary));
        // averagingDouble 因为有参数,所以用上面方法简单
//        personList.stream().map(Person::getSalary).collect(Collectors.averagingInt(value -> value));

        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getSalary).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getSalary));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getSalary));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }
}


class StreamTest_groupingBy {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));
        personList.add(new Person("Anni", 8200, 24, "female", "New York"));
        personList.add(new Person("Owen", 9500, 25, "male", "New York"));
        personList.add(new Person("Alisa", 7900, 26, "female", "New York"));

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }
}

class StreamTest_joining {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        String names = personList.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名：" + names);


        List<String> list = Arrays.asList("A", "B", "C");
        String string = list.stream().collect(Collectors.joining("-"));
        System.out.println("拼接后的字符串：" + string);
    }
}

// Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
class StreamTest_reducing {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 23, "male", "New York"));
        personList.add(new Person("Jack", 7000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 21, "female", "Washington"));

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getSalary, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getSalary).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }
}


class StreamTest_sorted {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();

        personList.add(new Person("Sherry", 9000, 24, "female", "New York"));
        personList.add(new Person("Tom", 8900, 22, "male", "Washington"));
        personList.add(new Person("Jack", 9000, 25, "male", "Washington"));
        personList.add(new Person("Lily", 8800, 26, "male", "New York"));
        personList.add(new Person("Alisa", 9000, 26, "female", "New York"));

        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getSalary).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getSalary() == p2.getSalary()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getSalary() - p1.getSalary();
            }
        }).map(Person::getName).collect(Collectors.toList());

        System.out.println("按工资升序排序：" + newList);
        System.out.println("按工资降序排序：" + newList2);
        System.out.println("先按工资再按年龄升序排序：" + newList3);
        System.out.println("先按工资再按年龄自定义降序排序：" + newList4);
    }
}

// 流也可以进行合并、去重、限制、跳过等操作。
class StreamTest_distinct {
    public static void main(String[] args) {
        String[] arr1 = {"a", "b", "c", "d"};
        String[] arr2 = {"d", "e", "f", "g"};

        Stream<String> stream1 = Stream.of(arr1);
        Stream<String> stream2 = Stream.of(arr2);
        // concat:合并两个流 distinct 清楚的：去重
        List<String> newList = Stream.concat(stream1, stream2).distinct().collect(Collectors.toList());
        System.out.println("流合并去重 distinct：" + newList);


        // limit：限制从流中获得前n个数据
        System.out.println("Stream.iterate(1, x -> x + 2) = " + Stream.iterate(1, x -> x + 2));

        List<Integer> collect = Stream.iterate(1, x -> x + 2).limit(10).collect(Collectors.toList());
        System.out.println("limit：" + collect);

        List<Integer> collect1 = Stream.iterate(1, x -> x + 1).limit(10).collect(Collectors.toList());
        System.out.println("limit collect1：" + collect1);

        // skip：跳过前n个数据
        List<Integer> collect2 = Stream.iterate(1, x -> x + 2).skip(3).limit(5).collect(Collectors.toList());
        System.out.println("skip：" + collect2);
    }
}
