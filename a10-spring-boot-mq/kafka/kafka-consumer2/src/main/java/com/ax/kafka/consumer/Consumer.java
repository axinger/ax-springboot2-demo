package com.ax.kafka.consumer;

import com.ax.kafka.api.Topic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Consumer {

    //    @KafkaListener(groupId = "simpleGroup", topics = com.ax.kafka.api.Topic.SIMPLE)
//@KafkaListener( topics = com.ax.kafka.api.Topic.SIMPLE)
    @KafkaListener(groupId = "simpleGroup_1", topics = Topic.SIMPLE)
    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, org.apache.kafka.clients.consumer.Consumer consumer, Acknowledgment ack) {

        System.out.println("=============消费者1-----1===========");
        System.out.println("消费者1--1 " + "consumer = " + consumer + "topic = " + topic + "value= " + record.value());


        Optional<Object> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
      /*      XyKafkaOutMsg build = new XyKafkaOutMsg();
            build.setGmtCreate(new Date());
            // TODO: 2019-05-15 此处fwBh就是消息队列的消息，后期使用修改
            build.setFwBh(System.currentTimeMillis());
            int insertOutMsg = xyKafkaOutMsgMapper.insertSelective(build);
            log.info("insertOutMsg result,{}",insertOutMsg==1 ? "成功" : "失败");
            linkedList.add(build.getId());*/
            ack.acknowledge(); // 手动提交消费消息
        }
        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }


    @KafkaListener(groupId = "simpleGroup_2", topics = Topic.SIMPLE)
    public void consumer1_2(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, org.apache.kafka.clients.consumer.Consumer consumer, Acknowledgment ack) {

        System.out.println("=============消费者1----2===========");
        System.out.println("消费者1--2 " + "consumer = " + consumer + "topic = " + topic + "value= " + record.value());


        Optional<Object> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
      /*      XyKafkaOutMsg build = new XyKafkaOutMsg();
            build.setGmtCreate(new Date());
            // TODO: 2019-05-15 此处fwBh就是消息队列的消息，后期使用修改
            build.setFwBh(System.currentTimeMillis());
            int insertOutMsg = xyKafkaOutMsgMapper.insertSelective(build);
            log.info("insertOutMsg result,{}",insertOutMsg==1 ? "成功" : "失败");
            linkedList.add(build.getId());*/
            ack.acknowledge(); // 手动提交消费消息
        }
        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }


}
