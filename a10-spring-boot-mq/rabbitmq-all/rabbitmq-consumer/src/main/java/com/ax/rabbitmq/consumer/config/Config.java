//package com.ax.rabbitmq.consumer.config;
//
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName Config.java
// * @Description TODO
// * @createTime 2022年02月16日 19:17:00
// */
//@Configuration
//public class Config {
//
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//
//    /**
//     * 定义监听器工厂
//     * 多个消费者
//     * @return
//     */
//    @Bean(name = "multiListenerContainer")
//    public SimpleRabbitListenerContainerFactory multiListenerContainer(){
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        //并发配置  最小5个，最大10个consumer
//        factory.setConcurrentConsumers(5);
//        factory.setMaxConcurrentConsumers(10);
//        //限流配置   consumer单位时间内接收到消息就是50条
//        factory.setPrefetchCount(50);
//        return factory;
//    }
//}
