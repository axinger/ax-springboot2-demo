package com.ax.demo_java;

public class Person implements Comparable {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
