package com.ax.rabbitmq.consumer.listener.fanout;

import com.ax.rabbitmq.consumer.util.MqBasicAck;
import com.rabbitmq.client.Channel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class FanoutListener {


    @SneakyThrows
    @RabbitListener(queues = "test_fanout_queue_A")
    public void receive1(String msg, Message message, Channel channel) {

        log.info("发布订阅模式  A收到消息 = {}", msg);

        TimeUnit.SECONDS.sleep(5);
        MqBasicAck.basicAck(channel, message);

    }

    @SneakyThrows
    @RabbitListener(queues = "test_fanout_queue_B")
    public void receive2(String msg, Message message, Channel channel) {

        log.info("发布订阅模式  B收到消息 = {}", msg);
        TimeUnit.SECONDS.sleep(5);
        MqBasicAck.basicAck(channel, message);
    }

//    public void receive(String msg, int receiver) throws InterruptedException {
//        // StopWatch 用来计时的
//        StopWatch watch = new StopWatch();
//        watch.start();
//        TimeUnit.SECONDS.sleep(2);
//        log.info("发布订阅模式 收到消息 = {}", msg);
//        watch.stop();
//        log.info("instance " + receiver + " [x] Done in " + watch.getTotalTimeSeconds() + "s");
//    }


}
