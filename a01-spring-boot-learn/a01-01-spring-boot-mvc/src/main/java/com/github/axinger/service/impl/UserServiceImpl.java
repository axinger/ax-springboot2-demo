package com.github.axinger.service.impl;

import com.github.axinger.dto.UserDTO;
import com.github.axinger.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO findById(Long id) {

        return UserDTO.builder()
                .id(id)
                .name("jim")
                .age(10L)
                .build();
    }
}
