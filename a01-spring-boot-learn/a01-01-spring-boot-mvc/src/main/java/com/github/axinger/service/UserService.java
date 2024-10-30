package com.github.axinger.service;


import com.github.axinger.dto.UserDTO;

public interface UserService {

     UserDTO findById(Long id);
}
