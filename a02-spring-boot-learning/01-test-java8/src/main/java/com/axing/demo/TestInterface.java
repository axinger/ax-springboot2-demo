package com.axing.demo;

//public interface TestInterface<T> {
public abstract class TestInterface<T> {
//    private T t;

    abstract void onBack();

//    public TestInterface() {
//        onBack(t);
//    }
}

class Test {
    public static void main(String[] args) {
        TestInterface<Student> testInterface = new TestInterface<Student>() {
            @Override
            public void onBack() {
                // TODO Auto-generated method stub
                System.out.println("this" + this);
                System.out.println("getClass" + getClass());
                System.out.println("" + getClass().getSuperclass());
                System.out.println("" + getClass().getGenericSuperclass());
            }
        };

        testInterface.onBack();
    }
}
