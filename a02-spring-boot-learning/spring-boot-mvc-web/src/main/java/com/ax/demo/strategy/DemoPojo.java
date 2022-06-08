package com.ax.demo.strategy;

public class DemoPojo {
    private String poolid;
    private String orderId;
    private String name;

    public DemoPojo(String poolid, String orderId, String name) {
        this.poolid = poolid;
        this.orderId = orderId;
        this.name = name;
    }

    public DemoPojo() {

    }

    public String getPoolid() {
        return poolid;
    }

    public void setPoolid(String poolid) {
        this.poolid = poolid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
