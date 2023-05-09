package com.axing.demo.stream;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.axing.demo.DeviceModel;
import com.axing.demo.Person;
import com.axing.demo.model.Staff;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    static List<Person> personList;

    static {
        personList = new ArrayList<>() {{
            add(Person.builder()
                    .id(1)
                    .name("jim")
                    .sex("男")
                    .age(10)
                    .address("安徽")
                    .build());
            add(Person.builder()
                    .id(1)
                    .name("lili")
                    .sex("女")
                    .age(10)
                    .address("安徽")
                    .build());
            add(Person.builder()
                    .id(1)
                    .name("Lucy")
                    .sex("女")
                    .age(18)
                    .address("安徽")
                    .build());
            add(Person.builder()
                    .id(1)
                    .name("小红")
                    .sex("女")
                    .age(20)
                    .address("江苏")
                    .build());
        }};
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll====");
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
    void test_mapToInt() {

        List<String> list2 = List.of("a", "bb", "ccc", "dddd");

        int sum = list2.stream().mapToInt(a -> a.length()).sum();
        System.out.println("sum = " + sum);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach=======");
    }

    /**
     * 按照月分组
     */
    @Test
    void test_group_date() {
        List<Staff> staffList = new ArrayList<>();
        staffList.add(new Staff("张三", DateUtil.parse("2022-01-01", "yyyy-MM-dd"), new BigDecimal(10)));
        staffList.add(new Staff("张三", DateUtil.parse("2022-01-02", "yyyy-MM-dd"), new BigDecimal(14)));
        staffList.add(new Staff("张三", DateUtil.parse("2022-01-03", "yyyy-MM-dd"), new BigDecimal(11)));

        staffList.add(new Staff("张三", DateUtil.parse("2022-02-03", "yyyy-MM-dd"), new BigDecimal(10)));
        staffList.add(new Staff("张三", DateUtil.parse("2022-02-04", "yyyy-MM-dd"), new BigDecimal(10)));


        // 日期月分组
        Map<String, List<Staff>> collect = staffList.stream().collect(
                Collectors.groupingBy(
                        o -> DateUtil.format(o.getDate(), "yyyy-MM")
                ));


        System.out.println("日期月分组 = " + collect);

        // 日期分组求和
        Map<String, BigDecimal> collect1 = staffList.stream().collect(
                Collectors.groupingBy(
                        o -> DateUtil.format(o.getDate(), "yyyy-MM"),
                        Collectors.mapping(Staff::getYield, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add)
                        )));
        System.out.println("日期分组求和 = " + collect1);

    }

    @Test
    void test_any() {
        List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);

        // 匹配第一个
        list.stream().findFirst().ifPresent(val -> {
            System.out.println("匹配第一个值：" + val);
        });

        list.parallelStream().filter(x -> x > 5).findFirst().ifPresent(val -> {
            System.out.println("匹配第一个值,大于5的:" + val);
        });

        // 匹配任意（适用于并行流）,会随机返回一个
        list.parallelStream().filter(x -> x > 5).findAny().ifPresent(val -> {
            System.out.println("匹配任意一个值,大于5的:" + val);
        });

        // 是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch(x -> x < 6);
        System.out.println("是否存在小于6的值：" + anyMatch);
    }

    @Test
    void test_boxed() {
        // boxed的作用就是将int类型的stream转成了Integer类型的Stream
        List<Integer> integers = Stream.of("1", "2")
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        System.out.println("integers = " + integers);
    }

    @Test
    void test_peek_map() {

        // peek 和 map 区别
        // peek 没有返回值,可以直接修改当前值
        List<Person> list1 = personList
                .stream()
                .peek((val) -> val.setName("pek:" + val.getName()))
                .toList();
        System.out.println("list1 = " + list1);
    }

    // 统计个数
    @Test
    void test_map_grouping_count() {
        List<DeviceModel> list = new ArrayList<>();

        list.add(DeviceModel.builder()
                .id("3")
                .state("2")
                .build());

        list.add(DeviceModel.builder()
                .id("1")
                .state("1")
                .build());


        list.add(DeviceModel.builder()
                .id("2")
                .state("2")
                .build());


        // 统计个数
        Map<String, Long> collect = list.stream().filter(val -> val.getState().equals("1")).collect(Collectors.groupingBy(DeviceModel::getId, Collectors.counting()));
        System.out.println("collect = " + collect);

        // map排序
        List<Map.Entry<String, Long>> collect2 = collect.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toList());

        System.out.println("map排序 = " + collect2);


        LinkedHashMap<String, List<DeviceModel>> collect3 = list.stream()
                .sorted(Comparator.comparing(DeviceModel::getId))
                .collect(Collectors.groupingBy(DeviceModel::getState, LinkedHashMap::new, Collectors.toList()));
        System.out.println("collect3 = " + JSON.toJSONString(collect3));


    }

    @Test
    void test_sum() {

        // 求总数
        long count = personList.size();
        System.out.println("count = " + count);

        // 求平均
        Double average = personList.stream().collect(Collectors.averagingDouble(Person::getAge));
        System.out.println("average = " + average);
        // averagingDouble 因为有参数,所以用上面方法简单
//        personList.stream().map(Person::getSalary).collect(Collectors.averagingInt(value -> value));

        // 求最大
        personList.stream().map(Person::getAge).max(Integer::compare).ifPresent(val -> {
            System.out.println("max = " + val);
        });

        // 求工资之和
        Integer sum = personList.stream().mapToInt(Person::getAge).sum();
        System.out.println("sum = " + sum);

        // 统计所有的
        IntSummaryStatistics collect1 = personList.stream().collect(Collectors.summarizingInt(Person::getAge));
        System.out.println("collect1.getCount() = " + collect1.getCount());
        System.out.println("collect1.getSum() = " + collect1.getSum());
        System.out.println("collect1.getMax() = " + collect1.getMax());
        System.out.println("collect1.getMin() = " + collect1.getMin());
        System.out.println("collect1.getAverage() = " + collect1.getAverage());

    }

    @Test
    void test_groupingBy_sum() {

        // 优先使用这个方法
        int sum = personList.stream()
                .mapToInt(Person::getAge)
                .sum();
        System.out.println("sum = " + sum);


        int sum2 = personList.stream()
                .collect(Collectors.summingInt(Person::getAge));
        System.out.println("sum2 = " + sum2);


        // 分组求和
        Map<String, Integer> collect = personList.stream()
                .collect(Collectors.groupingBy(
                                Person::getSex,
                                Collectors.summingInt(Person::getAge)
                        )
                );
        System.out.println("分组求和 = " + collect);

        Map<String, Long> collect1 = personList.stream()
                .collect(Collectors.groupingBy(
                                Person::getSex,
                                Collectors.counting()
                        )
                );
        System.out.println("分组求个数 = " + collect1);
    }


    @Test
    void test() {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> list = IntStream.range(0, nums.size() - 1)
                .map(i -> nums.get(i + 1) - nums.get(i))
                .boxed()
                .toList();

        System.out.println("list = " + list);

        Integer collect = IntStream.range(0, nums.size() - 1)
                .map(i -> nums.get(i + 1) - nums.get(i))
                .boxed()
                .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("collect = " + collect);

    }

    @Test
    void test_findFirst() {

        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        final Integer integer = list.stream().filter(o -> o > 3).findFirst().orElse(-1);
        System.out.println("integer = " + integer);


        final int i = LocalDateTime.now().getHour() / 2;
        System.out.println("i = " + i);

        System.out.println("i = " + (4 % 2));
    }

    // map排序
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
        final List<Integer> list = Lists.newArrayList(1, 2);
//        final List<List<Integer>> collect1 = Stream.of(list2).limit(1).collect(Collectors.toList());
//        System.out.println("collect1 = " + collect1);1, 2,3,4);

        List<Integer> list1 = list.stream().skip(2).limit(1).toList();
        System.out.println("list1 = " + list1);
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

        // 判空查找指定位置元素
        list2 = Lists.newArrayList(1, 2, 3, 4);
        final Integer s2 = Optional.ofNullable(list2).orElse(new ArrayList<>()).stream().skip(2).findFirst().orElse(-1);
        System.out.println("s2 = " + s2);

    }

    @Test
    void test_reverseOrder() {

        final List<Integer> list1 = Lists.newArrayList(1, 3, 2, 4);
        final List<Integer> list2 = list1.parallelStream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("倒序 = " + list2);

    }

    @Test
    void test_index() {

        final List<Integer> list1 = Lists.newArrayList(1, 3, 2, 4);

        List<Integer> list = list1.stream().map(val -> list1.indexOf(val)).toList();
        System.out.println("list = " + list);


        List<Integer> list2 = IntStream.rangeClosed(0, list1.size() - 1).mapToObj(i -> {
            System.out.println("i = " + i);
            return i;
        }).toList();

        System.out.println("list2 = " + list2);


        Stream.of("1", "2", "20", "3")
                .collect(Collectors.toCollection(ArrayDeque::new)) // or LinkedList
                .descendingIterator()
                .forEachRemaining(System.out::println);


        List<Integer> reverse = ListUtil.reverseNew(list1);
        System.out.println("reverse = " + reverse);
        System.out.println("list1 = " + list1);
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
                // 拍扁,同时检查 list中map是否null,给出默认空list
                .flatMap(el -> Optional.ofNullable(el).map(Map::values).orElse(new ArrayList<>()).stream())
                // 转成double
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
                    .age(18)
                    .build());
            add(Person.builder()
                    .id(1)
                    .age(17)
                    .build());
            add(Person.builder()
                    .id(1)
                    .age(16)
                    .build());
            add(Person.builder()
                    .id(1)
                    .age(20)
                    .build());

        }};

        List<Person> result0 = persons.stream().sorted(Comparator.comparing(Person::getAge)).collect(Collectors.toList());
        System.out.println("getAge = " + result0);

        List<Person> result = persons.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println("getName = " + result);

        List<Person> result2 = persons.stream().sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge)).collect(Collectors.toList());

        System.out.println("result2 = " + result2);


    }

    /**
     * Collectors.partitioningBy会根据值是否为true,把集合中的数据分割为两个列表，一个true列表，一个false列表。
     */
    @Test
    void test_partitioningBy() {
        Map<Boolean, List<Person>> collect = personList.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 18));
        System.out.println("collect = " + collect);
    }

    @Test
    void test_reduce() {
        /// 匹配和查找
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // 规约 反复结合起来, 求和,求积,类似用法
        Integer reduce1 = list.stream().reduce(1, (e1, e2) -> {
            System.out.println("reduce 1 : e1 = " + e1 + " e2 = " + e1);
            return e1 + e2;
        });
        System.out.println("reduce1 = " + reduce1);

        /// 没有初始值,返回Optional 类型
        Optional<Integer> reduce2 = list.stream().reduce((e1, e2) -> {
            System.out.println("reduce 1 : e1 = " + e1 + " e2 = " + e1);
            return e1 + e2;
        });
        System.out.println("reduce2 = " + reduce2);

        // reduce 第一个参数,给个默认值,
        Integer reduceSum = list.stream().reduce(Integer::sum).get();
        System.out.println("reduceSum = " + reduceSum);

        Integer reduceSum0 = list.stream().reduce(0, Integer::sum);
        System.out.println("reduceSum0 = " + reduceSum0);

        Integer reduceSum1 = list.stream().reduce(2, Integer::sum);
        System.out.println("reduceSum1 = " + reduceSum1);

        Integer mapToIntSum = list.stream().mapToInt(Integer::valueOf).sum();
        System.out.println("mapToIntSum = " + mapToIntSum);
    }

    // 去重

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
                .id(2)
                .build());
        personList.add(Person.builder()
                .id(3)
                .build());
        personList.add(Person.builder()
                .id(4)
                .build());
        personList.add(Person.builder()
                .id(5)
                .build());
        personList.add(Person.builder()
                .id(6)
                .build());

        final Optional<Person> optionalPerson = personList.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println("员工工资最大值：" + optionalPerson.get().getAge());

        final Optional<String> optional = personList.stream().max(Comparator.comparingInt(Person::getAge)).map(Person::getName);
        System.out.println("员工工资最大值的姓名 = " + optional.orElse(null));
    }
}


// 归约 减少 ，也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
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

        String string2 = list.stream().collect(Collectors.joining("-", "头", "尾"));
        System.out.println("拼接后的字符串：" + string2);
    }
}

// Collectors类提供的reducing方法，相比于stream本身的reduce方法，增加了对自定义归约的支持。
class StreamTest_reducing {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(Person.builder()
                .id(1)
                .name("jim")
                .age(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .name("tom")
                .age(1)
                .build());
        personList.add(Person.builder()
                .id(1)
                .name("jack")
                .age(1)
                .build());
        // 这3个效果一样的

        // 每个员工减去起征点后的薪资之和（这个例子并不严谨，但一时没想到好的例子）
        Integer sum = personList.stream().map(Person::getAge).reduce(0, (i, j) -> (i + j + 1));
        System.out.println("员工扣税薪资总和：" + sum);

        // stream的reduce
        Optional<Integer> sum2 = personList.stream().map(val -> val.getAge() + 1).reduce(Integer::sum);
        System.out.println("员工薪资总和：" + sum2.get());

        int sum1 = personList.stream().map(val -> val.getAge() + 1).mapToInt(Integer::valueOf).sum();
        System.out.println("sum1 = " + sum1);

        Optional<String> reduce = personList.stream().map(val -> val.getName()).reduce(String::join);
        System.out.println("String::join = " + reduce);
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
        // 多种排序 先按工资再按年龄升序排序
        //避免null
        List<String> newList3 = personList.stream()
                .sorted(Comparator.comparing(Person::getAge, Comparator.nullsLast(Comparator.naturalOrder())) // 判断null
                        .thenComparing(Person::getAge, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(Person::getName)
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
