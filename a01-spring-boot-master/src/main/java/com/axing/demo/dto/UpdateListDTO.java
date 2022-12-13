package com.axing.demo.dto;

import com.alibaba.fastjson2.JSON;
import com.axing.demo.entity.IpLog;

import java.util.List;

public class UpdateListDTO {

    private List<IpLog> list;
    private String name;

    public List<IpLog> getList() {
        return list;
    }

    public void setList(List<IpLog> list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
