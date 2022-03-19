package com.ax.mall.controller;

import com.ax.mall.dto.LoginDto;
import com.ax.mall.dto.LoginListDto;
import com.ax.mall.entity.TStudent;
import com.ax.mall.entity.User;
import com.ax.mall.entity.valid.PasswordGroup;
import com.ax.mall.entity.valid.UsernameGroup;
import com.ax.mall.entity.valid.ValidList;
import com.ax.mall.service.HttpClientService;
import com.ax.mall.service.TStudentService;
import com.ax.mall.util.axUtil.AxResultStateEnum;
import com.ax.mall.util.axUtil.ResponseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Slf4j
//@Api(tags = "测试")
public class TestController {


    @Autowired
    HttpClientService httpClientService;
    @Autowired
    private TStudentService studentService;

    @RequestMapping(value = "/test1.do")
    //@ApiOperation(value = "获取地区信息列表", notes = "获取地区信息列表")
    public ResponseEntity testdo1() {

        ResponseEntity<List<String>> entity = new ResponseEntity();

        List<String> list = new LinkedList<>();
        list.add("A");
        list.add("Baaa");

        entity.setBody(list);
        entity.setStateEnum(AxResultStateEnum.SUCCESS);
        log.info("啊啊啊啊啊啊啊");
        log.warn("哈哈哈哈哈");
        return entity;

    }

    @RequestMapping(value = "/test12.do")
    public Object test12() {

        ResponseEntity<List<String>> entity = new ResponseEntity();

        List<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("Baaa");

        entity.setBody(list);
        entity.setStateEnum(AxResultStateEnum.FAILURE);
        return entity;

    }

    @RequestMapping(value = "/toHttp.do")
    public Object toHttp() {
//        String url = "http://www.suning.com/";
        String url = "http://localhost:8080/getIpLog.do?id=88";

//        String url = "http://www.baidu.com/";
        return httpClientService.getClient(url, null, Object.class);
    }

    @RequestMapping("/path/{id}")
    public Integer testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("testPathVariable:" + id);
        return id;
    }

    //@ApiOperation(value = "RestGet", notes = "返回json数据")
    @GetMapping(value = "/restGet.do/{id}")
    @ResponseBody
    public org.springframework.http.ResponseEntity RestGet(@RequestParam(value = "name") String name,
                                                           @PathVariable("id") Integer id) {
        System.out.println("name = " + name);
        System.out.println("id = " + id);

        String spittle = "成功";
        HttpStatus status = spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;

        return new org.springframework.http.ResponseEntity(spittle, status);


    }

    //@ApiOperation(value = "/restPost", notes = "返回json数据")
    @PostMapping(value = "/restPost.do")
    @ResponseBody
    public Object restPost(@RequestParam(value = "name") String name) {
        System.out.println("name = " + name);
        return name + "RestPos";

    }

    @DeleteMapping(value = "/restDelete.do")
    @ResponseBody
    public Object restDelete(@RequestParam(value = "name") String name) {
        System.out.println("name = " + name);
        return name + "RestDelete";
    }

    @PutMapping(value = "/restPut.do")
    @ResponseBody
    public Object restPut(@RequestParam(value = "name") String name) {
        System.out.println("name = " + name);
        return name + "RestPut";

    }

    /**
     * Put 请求参数模型不要用 @RequestBody
     *
     * @param user
     * @return
     */
    @PutMapping(value = "/restPut2.do")
    @ResponseBody
    public Object restPut2(User user) {

        System.out.println("user = " + user);
        return user;

    }

    @RequestMapping(value = "/testList.do")
    public void listParam(@RequestBody List<String> list, String name) {

        System.out.println("list = " + list);
        System.out.println("name = " + name);
    }

    @PostMapping(value = "/jwt.do")
    public String jwtTest() {

        return "JWT成功";
    }

    @PostMapping(value = "/jwt2.do")
    public String jwtTest2() {

        return "JWT成功";
    }

    @GetMapping(value = "/test22.do")
    public Object login22(@Validated({UsernameGroup.class, PasswordGroup.class}) LoginDto loginEntity) {
        Map<String, Object> map = new HashMap();
        map.put("getUsername", loginEntity.getUsername());
        map.put("getPassword", loginEntity.getPassword());
        return map;
    }

    @GetMapping(value = "/test23.do")
    public Object login23(@Validated({UsernameGroup.class}) LoginDto loginEntity) {
        Map<String, Object> map = new HashMap();
        map.put("getUsername", loginEntity.getUsername());
        map.put("getPassword", loginEntity.getPassword());
        return map;
    }

    @GetMapping(value = "/test24.do")
    public Object login24(@Validated({PasswordGroup.class}) LoginDto loginEntity) {
        Map<String, Object> map = new HashMap();
        map.put("getUsername", loginEntity.getUsername());
        map.put("getPassword", loginEntity.getPassword());
        return map;
    }

    @GetMapping(value = "/test25.do")
    public Object login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password) {

        Map<String, Object> map = new HashMap();
        map.put("getUsername", username);
        map.put("getPassword", password);
        return map;

    }

    /**
     * 验证 list 必须要自定义一个list
     *
     * @param loginEntityList
     * @return
     */
    @PostMapping(value = "/test25.do")
    public Object login25(@RequestBody @Validated({UsernameGroup.class, PasswordGroup.class}) ValidList<LoginDto> loginEntityList) {

        loginEntityList.forEach(loginEntity ->
                System.out.println("loginEntity = " + loginEntity)
        );

        return "AA";
    }

    @GetMapping(value = "/500.do")
    public void error_500() {
        int i = 5 / 0;
    }

    //@ApiOperation(value = "参数为list,嵌套map")
    @PostMapping(value = "/loginList.do")
    public Object updateByList(@RequestBody(required = false) List<LoginDto> list) {
        System.out.println("list = " + list);
        return list;
    }

    //@ApiOperation(value = "参数为map,嵌套list,用object接受")
    @PostMapping(value = "/loginList2.do")
    public Object loginList2(@RequestBody(required = false) LoginListDto dto) {
        System.out.println("list = " + dto);
        return dto;
    }

    //@ApiOperation(value = "参数为map,嵌套list")
    @PostMapping(value = "/loginList3.do")
    public Object loginList3(@RequestBody(required = false) Map list) {
        System.out.println("list = " + list);
        return list;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/setSession", method = RequestMethod.GET)
    public Map<String, Object> firstResp(HttpServletRequest request, HttpSession session) {
//        HttpSession session =  request.getSession();
        session.setAttribute("userId", "用户id");
        Map<String, Object> map = new HashMap<>(16);
        map.put("getId", session.getId());
        map.put("getAttributeNames", session.getAttributeNames());

        return map;
    }

    /**
     * session共享
     */
    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public Object sessions(HttpServletRequest request, HttpSession session) {
//        HttpSession session =  request.getSession();

        Map<String, Object> map = new HashMap<>(16);
        map.put("getId", session.getId());
        map.put("getAttributeNames", session.getAttributeNames());
        map.put("userId", session.getAttribute("userId"));

        return map;
    }

    @GetMapping(value = "/student1")
    public Object student1() {
        final TStudent student = studentService.getById(1L);
        System.out.println("student = " + student);
        return student;
    }


    @GetMapping(value = "/student2")
    public Object student2() {
        Page<TStudent> page = new Page();
        final Page<TStudent> page1 = studentService.page(page);
        System.out.println("page1 = " + page1);
        System.out.println("ge1.getRecords() = " + page1.getRecords());
        return page1;
    }


}
