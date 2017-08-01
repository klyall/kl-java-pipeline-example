package com.lyall.example;

public class Greeter {

    private static final String DEFAULT_NAME = "World";

    public String hello() {
        return generateHelloMessage(DEFAULT_NAME);
    }

    public String hello(String name) {
        return generateHelloMessage(name);
    }

    private String generateHelloMessage(String name) {
        untestableMethod();

        return String.format("Hello %s!", name);
    }

    private void untestableMethod() {
        System.out.println("Not doing anything really but will show difference in coverage when using mutation testing");
    }
}
