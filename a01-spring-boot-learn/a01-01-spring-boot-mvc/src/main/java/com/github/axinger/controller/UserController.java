package com.github.axinger.controller;

import com.alibaba.fastjson2.JSON;
import com.axing.common.dto.PageDTO;
import com.axing.common.response.result.Result;
import com.axing.common.vo.PageResult;
import com.github.axinger.dto.UserDTO;
import com.github.axinger.model.Dog;
import com.github.axinger.model.PersonDTO;
import com.github.axinger.model.UserVO;
import com.github.axinger.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UserController")
@RequestMapping("/user")
public class UserController {

    @GetMapping("/one")
    public UserVO getUser() {
        UserVO user = new UserVO("jim", "123");
//        User user2 = new User();
        String name = user.username();
        System.out.println("name = " + name);

        UserVO.range range = new UserVO.range(1, 2);

        System.out.println(user.all());
        return user;
    }

    @GetMapping("/person")
    public PersonDTO person() throws InterruptedException {
        Thread.sleep(1000L);

        PersonDTO person = new PersonDTO();
        person.setName("jim");
        return person;
    }

    @Operation(summary = "post分页参数")
    @PostMapping("/page")
    public Result<List<Dog>> person(@RequestBody PageDTO<Dog> dto) {
        System.out.println("dto = " + dto);
        System.out.println(dto.getData().id());
        System.out.println("JSON.toJSONString(dto) = " + JSON.toJSONString(dto));


        PageResult<List<Dog>> result = new PageResult<>();
        result.setCode(200);
        result.setData(List.of(dto.getData()));
        result.setPageSize(dto.getPageSize());
        result.setPageNo(dto.getPageNo());
        result.setTotal(100);
        return result;
    }

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
}

