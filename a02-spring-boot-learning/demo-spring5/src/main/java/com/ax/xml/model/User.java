package com.ax.xml.model;

public class User {

    private String name;
    private String title;

    private String address;

    public User(String name, String title, String address) {
        this.name = name;
        this.title = title;
        this.address = address;
    }

    public void add() {
        System.out.println(" name = " + name + " title = " + title + " address = " + address);
    }
}
