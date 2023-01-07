package com.axing.demo.config;

import com.axing.demo.model.ApiVersion;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersonServiceAop {

    ////@Around("@within(自定义注解)")//自定义注解标注在的类上；该类的所有方法（不包含子类方法）执行aop方法
    // 定义切点表达式
    // @Pointcut("@annotation(com.axing.demo.model.ApiVersion)")
    // @Pointcut("@within(com.axing.demo.model.ApiVersion)")
    // public void service() {
    // }



    @Pointcut("@within(com.axing.demo.model.ApiVersion)")
    public void classPointCut() {
    }

    // @Around("service()")
    // @Around("@annotation(apiVersion)")
    // @Around("@annotation(apiVersion)")
    // @Around("within(com.axing.demo.model.ApiVersion)")
    // @Around("@annotation(com.axing.demo.model.ApiVersion)")
    @Around(value= "classPointCut() && @annotation(apiVersion)") // @annotation 获取参数
    public Object process2(ProceedingJoinPoint point, ApiVersion apiVersion) throws Throwable {
        System.out.println("Around==============================================");
        System.out.println("apiVersion.value() = " + apiVersion.value());
        // 访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null
                && args.length > 0
                && args[0].getClass() == String.class) {
            args[0] = args[0]+"改变后的参数1";
        }
        System.out.println("@Around：执行目标方法之前...");
        // 用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());
        return returnValue;
    }



}
