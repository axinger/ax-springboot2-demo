package com.github.axinger.controller;

import com.github.axinger.dto.UserDTO;
import com.github.axinger.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testGetUserById() throws Exception {
        UserDTO user = UserDTO.builder()
                .id(1L)
                .name("tom")
                .age(10L)
                .build();
        // 指定返回结果
        Mockito.when(userService.findById(1L)).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("tom"));
    }



}
