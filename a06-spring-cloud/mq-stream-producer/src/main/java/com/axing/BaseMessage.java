package com.axing;

import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: wqf
 * @Date: 2022/09/09
 * @Description: mq 发送消息的内容体基础内容
 */
@ToString
public class BaseMessage<T> implements Serializable {
    /**
     * 消息主题
     */
    private String topic;
    /**
     * 消息标签
     */
    private String tag;
    /**
     * 消息内容
     */
    private T data;
    /**
     *
     */
    private Map<String, Object> header;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getHeader() {
        return header;
    }

    public void setHeader(Map<String, Object> header) {
        this.header = header;
    }

    public BaseMessage(String topic, String tag, T data, Map<String, Object> header) {
        this.topic = topic;
        this.tag = tag;
        this.data = data;
        this.header = header;
    }

    public BaseMessage(String topic, String tag, T data) {
        this.topic = topic;
        this.tag = tag;
        this.data = data;
    }

    public BaseMessage(String topic,  T data) {
        this.topic = topic;
        this.data = data;
    }

    public BaseMessage() {
    }
}
