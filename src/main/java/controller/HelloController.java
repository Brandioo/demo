package controller;


import model.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloController {
    private final Template template;
    private final AtomicLong counter;
    private String message="Hello World";

    public HelloController(Template template, AtomicLong counter, String message) {
        this.template = template;
        this.counter = counter;
        this.message = message;
    }

    public HelloController(Template template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(){
        return message;
    }
}
