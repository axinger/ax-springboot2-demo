//package com.github.axinger.config;
//
//import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.JoinPoint;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class DynamicDataSourceAspect {
//
//    // 定义切点，匹配指定包路径下的所有类和方法
//    @Pointcut("execution(* com.github.axinger.db1.mapper..*.*(..))")
//    public void package1Pointcut() {
//    }
//
//
//    @Before("package1Pointcut()")
//    public void switchToDataSource1(JoinPoint joinPoint) {
//        DynamicDataSourceContextHolder.push("db_1"); // 切换到数据源1
//    }
//
//    @After("package1Pointcut()")
//    public void clearDataSource1(JoinPoint joinPoint) {
//        DynamicDataSourceContextHolder.clear(); // 清理数据源
//    }
//
//    @Pointcut("execution(* com.github.axinger.db2.mapper..*.*(..))")
//    public void package2Pointcut() {
//    }
//
//    @Before("package2Pointcut()")
//    public void switchToDataSource2(JoinPoint joinPoint) {
//        DynamicDataSourceContextHolder.push("db_2"); // 切换到数据源2
//    }
//
//    @After("package2Pointcut()")
//    public void clearDataSource2(JoinPoint joinPoint) {
//        DynamicDataSourceContextHolder.clear(); // 清理数据源
//    }
//}
