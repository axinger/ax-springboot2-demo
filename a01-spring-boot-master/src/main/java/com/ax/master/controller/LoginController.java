package com.ax.master.controller;

import com.ax.master.dto.LoginDto;
import com.ax.master.service.ILoginService;
import com.ax.master.util.error.TokenException;
import com.axing.common.response.result.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * @author axing
 */
//@Api(tags = "用户接口")
@Controller
public class LoginController extends BaseController {

    @Autowired
    private ILoginService loginService;


    @GetMapping(value = "/login.do")
    public String loginHtml() {
        return "login";
    }


    //@ApiOperation(value = "登录请求", notes = "返回json数据")
    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) throws TokenException {

        System.out.println("登录顺序: login.do username = " + username);

        Object object = this.loginService.login(username, password, this.request);

        System.out.println("object = " + object);

        return object;
    }

    @GetMapping(value = "/login22.do")
    public Object login22(@Validated LoginDto loginEntity) {

        Map<String, Object> map = new HashMap();
        map.put("getUsername", loginEntity.getUsername());
        map.put("getPassword", loginEntity.getPassword());
        return map;
    }


    @GetMapping(value = "/login2.do")
    @ResponseBody
    public Object login2() {

        List<String> list = new ArrayList<>();
        list.add("B");
        return Result.ok(list);

    }


    @RequestMapping(value = "/login3.do")
    @ResponseBody
    public Object login3() {

        List<String> list = new LinkedList<>();
        list.add("B");

        Result<List<String>> object = new Result<>();
        object.setCode(200);
        object.setMsg("eee");
        object.setData(list);
        return Result.ok(list);

    }

    /**
     * 重定向
     **/

    @RequestMapping(value = "/loginPage1")
    @ResponseBody
    public Object loginPage1(@RequestParam(value = "name") String name) {

        System.out.println("name = " + name);

        Map map = new HashMap();
        map.put("name", "收到:" + name);
        return map;
    }

    @RequestMapping(value = "/loginPage2")
    @ResponseBody
    public Object loginPage2(@RequestParam(value = "name") String name) {

        System.out.println("name = " + name);

        return "收到:" + name;
    }

    @RequestMapping("/loginPage3")
    public String loginPage3(@RequestParam(value = "name", required = false) String name,
                             RedirectAttributes redirectAttributes,
                             HttpServletResponse response) throws Exception {
        System.out.println("name = " + name);
        // 页面传参
        redirectAttributes.addFlashAttribute("username", name);
        // url传参
        redirectAttributes.addAttribute("name", name);
        return "redirect:/loginPage1";

    }

    @RequestMapping("/loginPage4")
    public void loginPage4(@RequestParam(value = "name", required = false) String name,
                           HttpServletResponse response) throws Exception {
        System.out.println("name = " + name);

        response.sendRedirect("/loginPage1?name=" + name);

    }

}
