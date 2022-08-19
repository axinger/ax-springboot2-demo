package com.axing.demo;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

    @Test
    void test_findFirst() {

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        final Integer integer = list.stream().filter(o -> o > 3).findFirst().orElse(-1);
        System.out.println("integer = " + integer);


        final int i = LocalDateTime.now().getHour() / 2;
        System.out.println("i = " + i);

        System.out.println("i = " + (4 % 2));
    }


    //map排序
    @Test
    void test_map() {
//        Map<String, Object> map = new HashMap();
        Map<String, Object> map = new LinkedHashMap();
        map.put("3", "c");
        map.put("1", "a");
        map.put("2", "b");


        System.out.println("map = " + map);

        // 按值排序 升序
        Map<String, Object> sorted = map.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));
        System.out.println("升序按值排序后的map: " + sorted);


        // 按值排序 升序
        Map<String, Object> collect = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, value -> value.getValue() + "aaa"));
        System.out.println("map改变value值 = " + collect);


        Map<String, Object> collect2 = map.entrySet().stream().map(val -> {
            val.setValue(val.getValue() + "ccc");
            return val;
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("map改变value值 = " + collect2);


    }

    @Test
    void Optional_null() {

        List listNull = null;
        Optional.ofNullable(listNull).orElse(new ArrayList<>()).stream().forEach(val -> System.out.println("Optional 和 Stream val = " + val));
    }

    public static Stream<Character> fromStringToStream(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }


        return list.stream();
//        return  Arrays.asList(str.toCharArray()).stream();
    }

    @Test
    void test_2_list() {

        List<Map<Object, Object>> list1 = Lists.newArrayList(new HashMap() {{
            put("id", "1");
            put("name", "jim");
            put("age", "");
        }}, new HashMap() {{
            put("id", "2");
            put("name", "tom");
            put("age", "");
        }});


        List<Map<Object, Object>> list2 = Lists.newArrayList(new HashMap() {{
            put("id", "1");
            put("age", "10");
        }}, new HashMap() {{
            put("id", "2");
            put("age", "12");
        }});


//        List resultList2 = list1.stream().map(m->{
//
//            final Map<Object, Object> map = list2.stream().filter(m2 -> Objects.equals(m.get("id"), m2.get("id")))
//                    .findFirst().orElse(new HashMap<>());
//
//            m.put("age",map.get("age"));
//            return m;
//
//        }).collect(Collectors.toList());
//
//
//        resultList2.stream().forEach(s-> System.out.println(s));


        list1.stream().forEach(m -> {

            final Object age = list2.stream().filter(m2 -> Objects.equals(m.get("id"), m2.get("id"))).findFirst().map(val -> val.get("age")).orElse("");

            m.put("age", age);

        });

        System.out.println("list1 = " + list1);

    }

    @Test
    void test_limit() {

        // 超出不回报错
        final List<Integer> list = Lists.newArrayList(1, 2, 3);

        list.stream().limit(3).forEach(System.out::println);
    }

    @Test
    void test_limit_null() {
        final List<Integer> list = Lists.newArrayList();
        list.stream().limit(1).forEach(val -> System.out.println("val = " + val));
        final List<Integer> collect = list.stream().limit(1).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        final List<Integer> list2 = null;

//        List<Integer> list3 = null;
//        Optional.ofNullable(list2).map(List::stream).ifPresent(val->{
//
//            list3 = val.collect(Collectors.toList());
//
//        });

//        final List<Integer> list2 = Lists.newArrayList

//        Stream.of(list2).limit(1).forEach(val-> System.out.println("val = " + val));
//
//        final List<Object> collect1 = Stream.of(list2).flatMap(e -> {
//            System.out.println("e = " + e);
//            return Stream.empty();
//        }).collect(Collectors.toList());
//        System.out.println("collect1 = " + collect1);

//        final List<List<Integer>> collect2 = Stream.generate(() -> list2).collect(Collectors.toList());


    }

    @Test
    void test_limit2() {

        // 超出不回报错
        final List<Integer> list = Lists.newArrayList();
//        final List<List<Integer>> collect1 = Stream.of(list2).limit(1).collect(Collectors.toList());
//        System.out.println("collect1 = " + collect1);1, 2,3,4);

        list.stream().skip(2).limit(1).forEach(System.out::println);
    }

    @Test
    void test_limit_findFirst() {

        // 超出不回报错
//        final List<Integer> list = Lists.newArrayList(1, 2,3,4);
        final List<Integer> list = Lists.newArrayList();
        final Integer integer = list.stream().findFirst().orElse(-1);

        System.out.println("integer = " + integer);

        List<Integer> list2 = null;
        list2 = Lists.newArrayList(1);
        final Integer val = Optional.ofNullable(list2).orElse(new ArrayList<>()).stream().findFirst().orElse(-1);
        System.out.println("val = " + val);

        //判空查找指定位置元素
        list2 = Lists.newArrayList(1, 2, 3, 4);
        final Integer s2 = Optional.ofNullable(list2).orElse(new ArrayList<>()).stream().skip(2).findFirst().orElse(-1);
        System.out.println("s2 = " + s2);

    }

    @Test
    void test_reverseOrder() {


        final List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4);
        final List<Integer> list2 = list1.parallelStream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("倒序 = " + list2);

    }

    // 拍扁
    @Test
    void test_list_map() {
        // list嵌套map 取map的值
//        String name = "jim";
        String name = null;
        final Map<String, Object> map = new HashMap() {{
            put("name", name);
            put("age", 10);
        }};
//        final Map<String, Object> map = null;
        System.out.println("map = " + map);
        final ArrayList<Map<String, Object>> list = Lists.newArrayList(map);

        final List<Object> collect = list.stream().flatMap(el -> {

            return Optional.ofNullable(el).map(Map::values).orElse(new ArrayList<>()).stream();
//            el.values().stream()


        }).collect(Collectors.toList());
        System.out.println("collect = " + collect);


        final List<Double> collect2 = list.parallelStream()
                //拍扁,同时检查 list中map是否null,给出默认空list
                .flatMap(el -> Optional.ofNullable(el).map(Map::values).orElse(new ArrayList<>()).stream())
                //转成double
                .map(el -> Double.parseDouble(Optional.ofNullable(el).orElse(0).toString())).collect(Collectors.toList());

        System.out.println("collect2 = " + collect2);
    }

    @Test
    void test3() {
        Object[] objects = new Object[]{1, 2, 3};

        Arrays.stream(objects).forEach(e -> System.out.println(e));

        Stream.of(objects).forEach(e -> System.out.println(e));

    }

    @Test
    void test1() {


        List<Person> list = new ArrayList<>() {
            {


                Person.builder()
                        .id(1)
                        .name("jim")
                        .age(9)
                        .build();

                Person.builder()
                        .id(2)
                        .name("tom")
                        .age(10)
                        .build();

                Person.builder()
                        .id(3)
                        .name("lili")
                        .age(11)
                        .build();

                Person.builder()
                        .id(1)
                        .name("lick")
                        .age(11)
                        .build();


            }
        };

        ArrayList<Person> collect = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getAge))), ArrayList::new));
        System.out.println("去重年龄重复的 = " + collect);


        /// 顺序流
        list.stream().forEach(System.out::println);

        // limit 截断流
        list.stream().limit(2).forEach(e -> {
            System.out.println("limit e = " + e);
        });

        // 跳过 和 limit 互补
        list.stream().skip(2).forEach(e -> {
            System.out.println("skip e = " + e);
        });

        // 去重: 筛选,根据 hashcode,equals,去重
        list.stream().distinct().forEach(e -> {
            System.out.println("distinct e = " + e);
        });


        /// parallelStream 并行流
        list.parallelStream().forEach(e -> {
            System.out.println("parallelStream e = " + e);
        });


        List<Person> mapList = list.stream().map(e -> {
            System.out.println("e = " + e);
            e.setName(e.getName() + "__A");
            return e;
        }).collect(Collectors.toList());
        System.out.println("mapList = " + mapList);


        List<Person> filterList = list.stream().filter(e -> {

            return e.getAge() > 10;
        }).collect(Collectors.toList());
        System.out.println("filterList = " + filterList);


        Set<Person> personSet = list.stream().filter(e -> e.getAge() > 10).collect(Collectors.toSet());
        System.out.println("personSet = " + personSet);

    }

    @Test
    void test2() {


        List<Person> list = new ArrayList<Person>() {
            {
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
            }
        };


        List<Person> list2 = new ArrayList<Person>() {
            {
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
                add(Person.builder()
                        .id(1)
                        .build());
            }
        };

        List<List<Person>> list3 = new ArrayList<>();
        list3.add(list);
        list3.add(list2);

        list3.stream().flatMap(e -> e.stream()).forEach(e -> {
            System.out.println("flatMap 拍扁 e = " + e);
        });
    }

    @Test
    public void test4() {

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        list.stream().flatMap(StreamTest::fromStringToStream).forEach(System.out::println);
    }

    @Test
    public void test5() {
        /// 排序
        List list = Arrays.asList(1, 2, 4, 3, -1);
        list.stream().sorted().forEach(System.out::println);

    }

    @Test
    void test6() {
        /// 排序
        List<Person> persons = new ArrayList<Person>() {{
            add(Person.builder()
                    .id(1)
                    .build());
            add(Person.builder()
                    .id(1)
                    .build());
            add(Person.builder()
                    .id(1)
                    .build());
            add(Person.builder()
                    .id(1)
                    .build());

        }};

        List<Person> result0 = persons.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        System.out.println("getAge = " + result0);

        List<Person> result = persons.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println("getName = " + result);

        List<Person> result2 = persons.stream().sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge)).collect(Collectors.toList());

        System.out.println("result2 = " + result2);


    }

    @Test
    void test7() {
        /// 匹配和查找
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        /// 是否全部 满足一个条件
        boolean allMatch = list.stream().allMatch(e -> e > 2);

        System.out.println("allMatch = " + allMatch);

        /// 任何一个满足条件
        boolean anyMatch = list.stream().anyMatch(e -> e > 2);
        System.out.println("anyMatch = " + anyMatch);


        /// 是否没有匹配的,
        boolean noneMatch = list.stream().noneMatch(e -> e > 2);
        System.out.println("noneMatch = " + noneMatch);
        Integer first = list.stream().findFirst().get();
        System.out.println("first = " + first);
        Optional<Integer> max = list.stream().max(Integer::compare);
        System.out.println("max = " + max);
        Optional<Integer> min = list.stream().min(Integer::compare);
        System.out.println("min = " + min);
        long count = list.stream().filter(e -> e > 3).count();
        System.out.println("count = " + count);

        // 规约 反复结合起来
        Integer reduce1 = list.stream().reduce(1, (e1, e2) -> e1 * e2);
        System.out.println("reduce1 = " + reduce1);

        /// 没有初始值,返回Optional 类型
        Optional<Integer> reduce2 = list.stream().reduce((e1, e2) -> e1 * e2);
        System.out.println("reduce2 = " + reduce2);

        Integer reduce = list.stream().reduce(0, Integer::sum);

        System.out.println("reduce = " + reduce);


    }

    //去重

    // 根据name,sex两个属性去重
    @Test
    void test_TreeSet() {

        List<Person> persons = new ArrayList();
        persons.add(Person.builder().id(1).name("jim").sex("0").build());
        persons.add(Person.builder().id(2).name("jim").sex("0").build());
        persons.add(Person.builder().id(3).name("jim").sex("1").build());

        List<Person> uniqueByNameAndSex = persons.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> o.getName() + o.getSex()))), ArrayList::new));

        System.out.println("uniqueByNameAndSex = " + uniqueByNameAndSex);

        List<Person> uniqueByNameAndSex1 = persons.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName).thenComparing(Person::getSex))), ArrayList::new));
        System.out.println("uniqueByNameAndSex1 = " + uniqueByNameAndSex1);


    }

    @Test
    void collectingAndThen_test() {

        /// 不知道作用
        Stream<String> stream = Stream.of("d", "a", "c", "d");
        List<String> list = stream.collect(Collectors.collectingAndThen(Collectors.toList(), Collections::<String>unmodifiableList));
        System.out.println(list);
    }


    @Test
    void count_test() {
        Stream<String> stream = Stream.of("1", "2", "3", "4");


        Long result = stream.collect(Collectors.counting());

        System.out.println(result);


        System.out.println(Arrays.asList("1", "2", "3", "4").stream().count());
    }

    @Test
    void groupingBy_test() {
        Stream<String> stream = Stream.of("apple", "apple", "pear", "banana");
        Map<String, Long> map = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }
}


class StreamTest2 {

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
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1, "tom", "man", 20, "安徽"));
        personList.add(new Person(2, "jack", "man", 22, "安徽"));
        personList.add(new Person(3, "jim", "man", 20, "江苏"));
        personList.add(new Person(4, "lili", "man", 24, "上海"));

        List<String> list = personList.stream().filter(x -> x.getAge() > 20)
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("age>20的 姓名：" + list);
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
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

        final Optional<Person> optionalPerson = personList.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println("员工工资最大值：" + optionalPerson.get().getAge());

        final Optional<String> optional = personList.stream().max(Comparator.comparingInt(Person::getAge)).map(Person::getName);
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
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

        // 求总数
        Long count = personList.stream().collect(Collectors.counting());
        // 求平均工资
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getAge));
        // averagingDouble 因为有参数,所以用上面方法简单
//        personList.stream().map(Person::getSalary).collect(Collectors.averagingInt(value -> value));

        // 求最高工资
        Optional<Integer> max = personList.stream().map(Person::getAge).collect(Collectors.maxBy(Integer::compare));
        // 求工资之和
        Integer sum = personList.stream().collect(Collectors.summingInt(Person::getAge));
        // 一次性统计所有信息
        DoubleSummaryStatistics collect = personList.stream().collect(Collectors.summarizingDouble(Person::getAge));

        System.out.println("员工总数：" + count);
        System.out.println("员工平均工资：" + average);
        System.out.println("员工工资总和：" + sum);
        System.out.println("员工工资所有统计：" + collect);
    }
}


class StreamTest_groupingBy {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());


        // 将员工按薪资是否高于2组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 2));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);


        //分组求和
        Map<String, Integer> collect = personList.stream()
                .collect(Collectors.groupingBy(
                                Person::getSex,
                                Collectors.summingInt(Person::getAge)
                        )
                );
        System.out.println("collect = " + collect);

    }
}

class StreamTest_joining {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

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
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().collect(Collectors.reducing(0, Person::getAge, (i, j) -> (i + j - 5000)));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(Person::getAge).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());
    }
}


class StreamTest_sorted {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();

        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .build());

        // 按工资升序排序（自然排序）
        List<String> newList = personList.stream().sorted(Comparator.comparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 按工资倒序排序
        List<String> newList2 = personList.stream().sorted(Comparator.comparing(Person::getAge).reversed())
                .map(Person::getName).collect(Collectors.toList());
        // 先按工资再按年龄升序排序
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getAge)).map(Person::getName)
                .collect(Collectors.toList());
        // 先按工资再按年龄自定义排序（降序）
        List<String> newList4 = personList.stream().sorted((p1, p2) -> {
            if (p1.getAge() == p2.getAge()) {
                return p2.getAge() - p1.getAge();
            } else {
                return p2.getAge() - p1.getAge();
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