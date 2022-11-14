package com.axing.demo;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable {

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    private String area;


    @Override
    public int compareTo(Object o) {
        if (o instanceof Person) {
            Person p = (Person) o;
            int sort;
            sort = name.compareTo(p.name);
//            if (sort == 0) {
//                sort = Integer.compare(age, p.age);
////                sort = age.compareTo(p.age);
//            }
            return sort;
//            return  Comparator.comparing(Person::getName).thenComparing(Person::getAge);

        }
        throw new RuntimeException("类型不匹配");
    }


}
