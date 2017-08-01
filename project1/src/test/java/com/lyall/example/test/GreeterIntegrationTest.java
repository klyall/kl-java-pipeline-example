package com.lyall.example.test;

import com.lyall.example.Greeter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// Not really an integration test but here to show some code coverage
public class GreeterIntegrationTest {

    @Test
    public void shouldGiveCustomisedMessage() {
        String name  = "Bob";

        Greeter greeter = new Greeter();
        String message = greeter.hello(name);

        assertThat("Message", message, is(equalTo("Hello Bob!")));
    }
}
