package com.ax.demo.email;

import com.ax.demo.error.ServiceException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Email;

@RestController
@Validated
public class EmailController {

    @Resource
    EmailService emailService;

    @RequestMapping(value = "/sendEmail")
    public Object sendEmail(@RequestParam(value = "email") @Email(message="错误的邮箱格式") String email) throws ServiceException {

        System.out.println("email = " + email);
        return emailService.sendTextEmail(email);
    }

/*    @RequestMapping(value = "/sendEmail")
    public Object sendEmail2(@Validated Email email){
        System.out.println("email = " + email);

//        emailService.sendTextEmail(email);


        return "cg";
    }*/

}
