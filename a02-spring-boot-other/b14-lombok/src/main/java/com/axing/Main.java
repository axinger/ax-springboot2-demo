package com.axing;

import com.axing.common.util.json.JsonUtil;
import com.axing.model.GetterSetterExample;
import com.axing.model.Person;

public class Main {
    public static void main(String[] args) {

        Person person = new Person();

        person.aFirst("aFirst");


        String string = JsonUtil.writeValueAsString(person);
        System.out.println("string = " + string);
    }
}


class Main2 {
    public static void main(String[] args) {


        GetterSetterExample example = new GetterSetterExample();
        example.setAge(1);

    }
}
