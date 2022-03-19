package com.ax.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.ax.demo.dto.StudentDto;
import com.ax.demo.entity.Student;
import com.ax.demo.helloword.Injection;
import com.ax.demo.model.TPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xing
 */
@RestController
public class TestController {

    @Autowired
    private TPerson person;
    @Autowired
    private Injection injection;

    @RequestMapping(value = "/test0", produces = "application/json;charset=UTF-8")
    public Object test(
            @RequestBody StudentDto studentDto) {
        System.out.println("studentDto = " + studentDto);
        Map<String, Object> map = new HashMap<>(2);

        map.put("date", studentDto);

        return map;
    }

    /**
     * @ 加粗 下划线
     * @RequestBody能把简单json结构参数转换成实体类，
     */
    @RequestMapping(value = "/test1")
    public Map<String, Object> test1(
            @RequestBody @Validated StudentDto studentDto,
            @RequestHeader Map header,
            @RequestHeader("User-Agent") String userAgent) {
        System.out.println("studentDto = " + studentDto);
        System.out.println("header = " + header);
        System.out.println("userAgent = " + userAgent);


        Map<String, Object> map = new HashMap<>();

        map.put("date", studentDto);

        return map;
    }

    @RequestMapping(value = "/test11")
    public Map<String, Object> test11(
            @RequestHeader Map header,
            @RequestHeader("User-Agent") String userAgent
//            @CookieValue String _ga,
//            @Cookie cookie
    ) {

        System.out.println("header = " + header);
        System.out.println("userAgent = " + userAgent);
//        System.out.println("_ga = " + _ga);
//        System.out.println("cookie = " + cookie);


        Map<String, Object> map = new HashMap<>();
        map.put("User-Agent", userAgent);


        return map;
    }

    @RequestMapping(value = "/test2")
    @ResponseBody
    public Object test2(
            @RequestHeader(name = "token") String token,
            @RequestHeader Object id,
            StudentDto studentDto) {
        System.out.println("studentDto2 = " + studentDto);
        System.out.println("token = " + token);
        System.out.println("id = " + id);
        Map<String, Object> map = new HashMap<>();

        map.put("date", studentDto);

        return map;
    }

    @RequestMapping(value = "/test21")
    @ResponseBody
    public Map<String, Object> test2_1(
            String name,
            Integer age,
            @RequestHeader Map header) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(name);
        studentDto.setAge(age);
        System.out.println("studentDto = " + studentDto);
        System.out.println("header = " + header);

        Map<String, Object> map = new HashMap<>();
        map.put("date", studentDto);
        return map;
    }

    @RequestMapping(value = "/test22")
    @ResponseBody
    public Map<String, Object> test22(@RequestBody StudentDto studentDto, @RequestHeader Map header) {

        System.out.println("studentDto = " + studentDto);
        System.out.println("header = " + header);

        Map<String, Object> map = new HashMap<>();
        map.put("date", studentDto);
        return map;
    }

    @RequestMapping(value = "/test3")
    public Object test3(@RequestBody List<String> list) {
        return list;
    }

    @RequestMapping(value = "/test4", produces = {"application/json;charset=UTF-8"})
    public Object test4() {
        return "test4";
    }

    @RequestMapping(value = "/test5")
    public Object test5() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", "test5");
        return map;
    }

    @RequestMapping(value = "/test6")
    public Model test6(Model model) {

        model.addAttribute("d", "ddd");
        return model;
    }

    public void init() {


        Student student = new Student(1, "jim");


        Student.StudentBuilder st = Student.builder().id(1).name("jim");

        st.name("name");
        st.id(1);
        st.build().getName();

    }

    @GetMapping(value = "/person")
    public TPerson person() {
        return person;
    }

    /**
     * /cars/{path}?xx=xx&aa=aa   queryString 查询字符串
     * <p>
     * /cars/{path; low=32;brand=byd,id} 矩阵变量, brand 是一个数组
     * <p>
     * cookie被禁用可以用矩阵变量传递
     * <p>
     * 默认禁用矩阵变量
     */
    /// /cars/sell;low=32;brand=byd,id
    @GetMapping(value = "/cars/{path}")
    public Map test31(
            @PathVariable("path") String path,
            @MatrixVariable("low") Integer low,
            @MatrixVariable("brand") List<String> brand) {

        System.out.println("/cars/sell");


        Map map = new HashMap();
        map.put("low", low);
        map.put("brand", brand);
        map.put("path", path);
        return map;
    }

    @GetMapping(value = "/injection")
    @ResponseBody
    public void testHelloWord() {

        injection.doSome();
    }


    @GetMapping(value = "/nullValue")
    @ResponseBody
    public Object nullValue() {
        List list = null;
        Map map1 = null;

        Map map = new HashMap(16);
        map.put("string", null);
        map.put("list", list);
        map.put("map1", map1);
        map.put("date", new Date());


        return JSONObject.toJSON(map);
    }

}
