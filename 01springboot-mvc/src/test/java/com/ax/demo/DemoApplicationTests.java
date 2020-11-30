package com.ax.demo;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Callable;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {


    }

    @Test
    void _Guava() {


    }

    public static <e> void main(String[] args) {
        /// valueOf 是 对应的名称
//		String param= "FAST";
		int param= 2;
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
       try{
           String name2 = Preconditions.checkNotNull(param2,"param2 is null"); // NullPointerException

           System.out.println("name2 = " + name2);
       }catch (Error e){

       }
        System.out.println("name2 = " +  ObjectUtil.isNotEmpty(param2));


        ImmutableSet<String> immutableSet = ImmutableSet.of("a", "b", "c");
        immutableSet.forEach(System.out::println);
    }


    public User action2() {

        return null;
    }
}



