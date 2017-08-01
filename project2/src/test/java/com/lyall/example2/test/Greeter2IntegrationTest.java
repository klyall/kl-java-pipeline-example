package com.lyall.example2.test;

import com.lyall.example2.Greeter2;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// Not really an integration test but here to show some code coverage
public class Greeter2IntegrationTest {

    @Test
    public void shouldGiveCustomisedMessage() {
        String name  = "Bob";

        Greeter2 greeter = new Greeter2();
        String message = greeter.hello(name);

        assertThat("Message", message, is(equalTo("Hello Bob!")));
    }
}
