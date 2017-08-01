package com.lyall.example.test;

import com.lyall.example.Greeter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GreeterTest {

    @Test
    public void shouldGiveDefaultMessage() {
        Greeter greeter = new Greeter();
        String message = greeter.hello();

        assertThat("Message", message, is(equalTo("Hello World!")));
    }

    @Test
    public void shouldGiveCustomisedMessage() {
        String name  = "Bob";

        Greeter greeter = new Greeter();
        String message = greeter.hello(name);

        assertThat("Message", message, is(equalTo("Hello Bob!")));
    }
}
