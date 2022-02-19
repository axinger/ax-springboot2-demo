//package com.ax.rabbitmq.producer.config;
//
//import com.ax.rabbitmq.producer.service.IMessageService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Optional;
//
///**
// * @author xing
// * @version 1.0.0
// * @ClassName CallbackConfig.java
// * @Description TODO
// * @createTime 2022年02月18日 20:24:00
// */
//@Component
//@Slf4j
//public class CallbackConfig implements  RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
//
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//
//    @PostConstruct
//    public  void init(){
//        /**
//         * correlationData 相关配置
//         * ack 是否成功收到消息
//         * cause 失败原因
//         * */
//        rabbitTemplate.setConfirmCallback(this);
//
//        /**
//         * 回退模式
//         * 发送给交换机后, 路由到queue失败,才会执行
//         * 处理消息模式
//         *  1.丢弃,默认
//         *  2.返回给发送方
//         *
//         *  事务方式 性能较差
//         * */
//
//        /**
//         * 设置处理模式
//         * */
//        rabbitTemplate.setMandatory(true);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//
//    /**
//     * 消费者收到消息
//     * */
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//
//        Optional.ofNullable(correlationData).map(CorrelationData::getId).ifPresent((id)->{
//            System.out.println("id = " + id);
//        });
//
////        final String id = Optional.ofNullable(correlationData).map(CorrelationData::getId).orElse("");
//
//
//        log.info("消费者收到消息: ack=" + ack + "  cause=" + cause);
//        log.info("correlationData -->" + correlationData);
//        if (ack) {
//            log.info("---- confirm ----ack==true  cause=" + cause);
//        } else {
//            log.info("---- confirm ----ack==false  cause=" + cause);
//        }
//    }
//
//    /**
//     * 消费者回退消息
//     * */
//    @Override
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        log.info("消费者回退消息: ----replyCode=" + replyCode + " replyText=" + replyText + " ");
//    }
//}
