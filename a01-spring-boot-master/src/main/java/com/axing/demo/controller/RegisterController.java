package com.axing.demo.controller;

import com.axing.common.response.result.Result;
import com.axing.demo.service.IRegisterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author axing
 */
@RestController
//@Api(description = "用户接口")
public class RegisterController {


    @Autowired
    private IRegisterService registerService;


    public RegisterController(IRegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/registerPage.do")
    public ModelAndView registerPage() {

        return new ModelAndView("register");
    }


    @RequestMapping(value = "/registerUser.do")
    @ResponseBody
    public Object registerUser(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

        boolean register = this.registerService.register(username, password, 1);


        if (register) {
            return Result.ok().message("注册成功");
        } else {
            return Result.fail().message("用户已存在");
        }
    }


    @RequestMapping(value = "/checkUserName.do")
    @ResponseBody
    public boolean checkUsername(@Param(value = "username") String username) {

        boolean checkUsername = registerService.checkUsername(username);

        System.out.println("checkUsername>>>> " + checkUsername);

        return checkUsername;

    }
}
