package com.github.axinger.service.impl;

import com.github.axinger.model.dto.DateDTO;
import com.github.axinger.model.dto.LoginDTO;
import com.github.axinger.model.dto.ParamDTO;
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
        System.out.println("name = " + name);
    }

    @Override
    public void strNotNull2(String name) {

    }

    @Override
    public void strNotNull3(String name) {

    }

    @Override
    public void listNotEmpty(List<String> list) {

    }

    @Override
    public void funOne(ParamDTO one) {
        System.out.println("one = " + one);
    }

    @Override
    public void funList(List<ParamDTO> list) {
        System.out.println("list = " + list);
    }

    @Override
    public void testDate(DateDTO dto) {

    }

    @Override
    public void loginDTO(LoginDTO dto) {
        System.out.println("dto = " + dto);
    }

    @Override
    public void loginDTO2(LoginDTO dto) {
        System.out.println("dto = " + dto);
    }

    @Override
    public void loginDTO3(LoginDTO dto) {
        System.out.println("dto = " + dto);
    }

    @Override
    public void loginDTO4(LoginDTO dto) {
        System.out.println("dto = " + dto);
    }

    @Override
    public void loginDTO5(LoginDTO dto) {
        System.out.println("dto = " + dto);
    }

}
