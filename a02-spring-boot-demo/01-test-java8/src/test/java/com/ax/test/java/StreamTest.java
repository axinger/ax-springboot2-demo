package com.ax.test.java;


import com.ax.demo.Person;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {


    @Test
    void test_2_list() {

        List<Map<Object, Object>> list1 = Lists.newArrayList(
                new HashMap() {{
                    put("id", "1");
                    put("name", "jim");
                    put("age", "");
                }},
                new HashMap() {{
                    put("id", "2");
                    put("name", "tom");
                    put("age", "");
                }}
                );


        List<Map<Object, Object>> list2 = Lists.newArrayList(
                new HashMap() {{
                    put("id", "1");
                    put("age", "10");
                }},
                new HashMap() {{
                    put("id", "2");
                    put("age", "12");
                }}
        );



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


        list1.stream().forEach(m->{

            final Object age = list2.stream().filter(m2 -> Objects.equals(m.get("id"), m2.get("id")))
                    .findFirst().map(val->val.get("age")).orElse("");

            m.put("age",age);

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
                .flatMap(el -> Optional.ofNullable(el)
                        .map(Map::values)
                        .orElse(new ArrayList<>())
                        .stream()
                )
                //转成double
                .map(el -> Double.parseDouble(Optional.ofNullable(el)
                        .orElse(0).toString())
                )
                .collect(Collectors.toList());

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


        List<Person> list = new ArrayList<Person>() {
            {
                add(new Person("jim", 9));
                add(new Person("jim", 10));
                add(new Person("jim", 11));
                add(new Person("jim", 12));
                add(new Person("jim", 12));
            }
        };
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


        Set<Person> personSet = list.stream()
                .filter(e -> e.getAge() > 10)
                .collect(Collectors.toSet());
        System.out.println("personSet = " + personSet);

    }

    @Test
    void test2() {


        List<Person> list = new ArrayList<Person>() {
            {
                add(new Person("jim", 9));
                add(new Person("jim", 10));
                add(new Person("jim", 11));
                add(new Person("jim", 12));
            }
        };


        List<Person> list2 = new ArrayList<Person>() {
            {
                add(new Person("jim", 9));
                add(new Person("jim", 10));
                add(new Person("jim", 11));
                add(new Person("jim", 12));
            }
        };

        List<List<Person>> list3 = new ArrayList<>();
        list3.add(list);
        list3.add(list2);

        list3.stream().flatMap(e -> e.stream()).forEach(e -> {
            System.out.println("flatMap 拍扁 e = " + e);
        });
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
            add(new Person("b", 10));
            add(new Person("b", 9));
            add(new Person("a", 9));
            add(new Person("a", 10));

        }};

        List<Person> result0 = persons.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println("getAge = " + result0);

        List<Person> result = persons.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList());
        System.out.println("getName = " + result);

        List<Person> result2 = persons.stream()
                .sorted(Comparator.comparing(Person::getName)
                        .thenComparing(Person::getAge))
                .collect(Collectors.toList());

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
        persons.add(Person.builder().id("1").name("jim").sex("0").build());
        persons.add(Person.builder().id("2").name("jim").sex("0").build());
        persons.add(Person.builder().id("3").name("jim").sex("1").build());

        List<Person> uniqueByNameAndSex = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(o -> o.getName() + o.getSex()))),
                        ArrayList::new)
        );

        System.out.println("uniqueByNameAndSex = " + uniqueByNameAndSex);

        List<Person> uniqueByNameAndSex1 = persons.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(() ->
                                new TreeSet<>(Comparator.comparing(Person::getName).thenComparing(Person::getSex))),
                        ArrayList::new)
        );
        System.out.println("uniqueByNameAndSex1 = " + uniqueByNameAndSex1);


    }


}
