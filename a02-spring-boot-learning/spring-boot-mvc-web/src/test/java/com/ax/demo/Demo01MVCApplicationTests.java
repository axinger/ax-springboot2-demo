package com.ax.demo;

import cn.hutool.core.util.ObjectUtil;
import com.ax.demo.config.DemoConfig;
import com.ax.demo.model.Dog;
import com.ax.demo.model.Person;
import org.assertj.core.util.Preconditions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.concurrent.Callable;

@SpringBootTest
class Demo01MVCApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext context;

    public static <e> void main(String[] args) {
        /// valueOf 是 对应的名称
//		String param= "FAST";
        int param = 2;
        Strategy strategy = Strategy.valueOf(param);
        strategy.run();

//		for (Strategy strategy1:
//		Strategy.values()) {
//			System.out.println(strategy1.values());
//		}

//		System.out.println("Strategy.values() ="+Strategy.values().toString());
//		//valueOf 内部重写 该方法
//		Status status =  Status.valueOf(2);

//		Status status =  Status.valueOf(1);
//		status.run();
//		System.out.println("status.statusCode = " + status.getStatusCode());

//		for (Status strategy1:
//				Status.values()) {
//			System.out.println(strategy1);
//		}

//		User user = new User();
////		user.setName("ming");
//		user.setAge(23);
//
//
//		String userName = Optional.ofNullable(user)
//				.map(a->{
//					return  a.getName();
//				}).orElse("jim");
//
//		System.out.println("userName = " + userName);

        User user = new User();
        user.setId(1);
        user.setName("jim");
        user.setAge(1);
        user.setAddress("kk");

        user.test(() -> {
            System.out.println("先做这里事情");
            return "jim";
        }).test((Callable<String>) () -> {
            System.out.println("先做这里事情");
            return "tom";
        });

        user.test2(() -> {
            System.out.println("先做这里事情-2222");
        });

        String param1 = "AAA";
        String name = Preconditions.checkNotNull(param1);
        System.out.println(name); // AAA
        String param2 = null;
        try {
            String name2 = Preconditions.checkNotNull(param2, "param2 is null"); // NullPointerException

            System.out.println("name2 = " + name2);
        } catch (Error e) {

        }
        System.out.println("name2 = " + ObjectUtil.isNotEmpty(param2));

    }

    @Test
    void contextLoads() {


    }

    @Test
    public void getHello() throws Exception {

        /**
         * 1、mockMvc.perform执行一个请求。
         * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
         * 3、ResultActions.param添加请求传值
         * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
         * 5、ResultActions.andExpect添加执行完成后的断言。
         * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
         *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
         * 5、ResultActions.andReturn表示执行完成后返回相应的结果。
         */

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .param("name", "lvgang")
                        .accept(MediaType.TEXT_HTML_VALUE))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果

        Assert.assertEquals(200, status);                        //断言，判断返回代码是否正确
        Assert.assertEquals("hello lvgang", content);            //断言，判断返回的值是否正确
    }

    public User action2() {

        return null;
    }


    @Test
    void Test_config() {

        System.out.println("person01 = " + context.getBean("person01", Person.class));

        DemoConfig demoConfig = context.getBean(DemoConfig.class);
        Person person1 = demoConfig.person01();

        Person person2 = demoConfig.person01();
        System.out.println("是否 proxyBeanMethods " + (person1 == person2));

        String[] dogNames = context.getBeanNamesForType(Dog.class);
        System.out.println("dogNames = " + Arrays.toString(dogNames));

    }

}



