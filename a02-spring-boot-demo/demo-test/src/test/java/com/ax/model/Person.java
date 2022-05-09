package com.ax.model;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable {
    private String name;
    private Integer age;

    private String id;
    private String sex;


    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }



    @Override
    public boolean equals(Object o) {
        System.out.println("equals..............");
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person person = (Person) o;

        if (!name.equals(person.name)) {
            return false;
        }
        return age.equals(person.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age.hashCode();
        return result;
    }

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
