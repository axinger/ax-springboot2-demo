package com.github.axinger.producer;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component

public class PulsarProducer {

    @Autowired
    private PulsarTemplate template;


    public void send(Map message) {
        try {
            template.send("bootTopic", message);
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 有 deliverAfter 和 deliverAt 两种方式。
     * 其中 deliverAt 可以指定具体的时间戳；
     * deliverAfter 可以指定在当前多长时间后执行。
     * 两种方式的本质是一样的，Client 会计算出时间戳送到 Broker。
     * <p>
     * 延时消息的时长取值范围为0 - 864000秒（0秒 - 10天）。如10月1日12:00开始，最长可以设置864000秒。
     * 延时消息不可以使用 batch 模式发送，请在创建 producer 的时候把 enableBatch 参数设为 false。
     * 延时消息的消费模式仅支持使用 Shared 模式进行消费，否则会失去延时效果（Key-shared 也不支持）。
     */
    public void deliverAfter() {

        try {
            Map map = new HashMap<>();
            map.put("date", LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss"));
            map.put("data", "发送延迟消息");

            template.createMessage("deliverAfterTopic", map)
                    .deliverAfter(10, TimeUnit.SECONDS)
                    .send().toString();

        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }
}
