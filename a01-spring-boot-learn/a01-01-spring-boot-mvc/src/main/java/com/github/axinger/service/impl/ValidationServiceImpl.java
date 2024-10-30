package com.github.axinger.service.impl;

import com.github.axinger.dto.DateDTO;
import com.github.axinger.dto.ObjectDTO;
import com.github.axinger.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void strNo(String name) {

    }


    @Override

    public void strNotBlank(String name) {

        System.out.println("name = " + name);
    }

    @Override
    public void strNotEmpty(String name) {
        System.out.println("name = " + name);
    }

    @Override
    public void strNotNull(String name) {

    }

    @Override
    public void funOne(ObjectDTO one) {
        System.out.println("one = " + one);
    }

    @Override
    public void funList(List<ObjectDTO> list) {
        System.out.println("list = " + list);
    }

    @Override
    public void testDate(DateDTO dto) {

    }

}
