package com.lyall.example2.test;

import com.lyall.example2.Greeter2;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Greeter2Test {

    @Test
    public void shouldGiveDefaultMessage() {
        Greeter2 greeter = new Greeter2();
        String message = greeter.hello();

        assertThat("Message", message, is(equalTo("Hello World!")));
    }

    @Test
    public void shouldGiveCustomisedMessage() {
        String name  = "Bob";

        Greeter2 greeter = new Greeter2();
        String message = greeter.hello(name);

        assertThat("Message", message, is(equalTo("Hello Bob!")));
    }
}
