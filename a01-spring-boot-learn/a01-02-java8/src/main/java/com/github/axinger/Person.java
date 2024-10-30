package com.github.axinger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//public class Person implements Comparable {
public class Person {

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String address;


//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof Person p) {
//            int sort;
//            sort = name.compareTo(p.name);
////            if (sort == 0) {
////                sort = Integer.compare(age, p.age);
//////                sort = age.compareTo(p.age);
////            }
//            return sort;
////            return  Comparator.comparing(Person::getName).thenComparing(Person::getAge);
//
//        }
//        throw new RuntimeException("类型不匹配");
//    }


}
