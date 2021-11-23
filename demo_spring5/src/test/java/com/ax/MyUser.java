package com.ax;


public class MyUser {


    public  DoFunction doTest1(int a, int b) {

        return (message) -> {

//            if (a > b) ;
            System.out.println("message = " + message);

        };
    }


    public static void main(String[] args) {
        MyUser myUser = new MyUser();

        myUser.doTest1(1,2).message("111");
    }
}


