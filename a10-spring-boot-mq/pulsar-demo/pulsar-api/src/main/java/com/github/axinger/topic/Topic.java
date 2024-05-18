package com.github.axinger.topic;

public interface Topic {

    String EXCLUSIVE_TOPIC = "ExclusiveTopic";
    String FAILOVER_TOPIC = "FailoverTopic";
    String SHARED_TOPIC = "SharedTopic";
    String KEY_SHARED_TOPIC = "Key_SharedTopic";


    /**
     * 延迟  subscriptionType = SubscriptionType.Shared, 延迟消息必须是共享模式
     * 会有1秒延迟
     */
    String DELIVER_AFTER_TOPIC = "deliverAfterTopic";

    /**
     * 定时,转换成时间戳,精确到毫秒
     */
    String DELIVER_AT_TOPIC = "deliverAtTopic";

}
