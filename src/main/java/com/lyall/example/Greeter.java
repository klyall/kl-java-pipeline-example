package com.lyall.example;

import java.util.Optional;

public class Greeter {

    private static final String DEFAULT_NAME = "World";

    public String hello() {
        return generateHelloMessage(Optional.empty());
    }

    public String hello(String name) {
        return generateHelloMessage(Optional.of(name));
    }

    private String generateHelloMessage(Optional<String> name) {
        String messageName = DEFAULT_NAME;

        if (name.isPresent()) {
            messageName = name.get();
        }

        untestableMethod();

        return String.format("Hello %s!", messageName);
    }

    private void untestableMethod() {
        System.out.println("Not doing anything really but will show difference in coverage when using mutation testing");
    }
}
