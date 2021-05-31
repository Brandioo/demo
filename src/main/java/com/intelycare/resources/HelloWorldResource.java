package com.intelycare.resources;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;

import com.intelycare.api.Saying;
import com.intelycare.core.Template;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.DateTimeParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.ws.rs.POST;

import javax.ws.rs.QueryParam;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
//    private final String template;
//    private final String defaultName;
//    private final AtomicLong counter;

//    public HelloWorldResource(String template, String defaultName) {
//        this.template = template;
//        this.defaultName = defaultName;
//        this.counter = new AtomicLong();
//    }
//
//    @GET
//    @Timed
//    public Saying sayHello(@QueryParam("name") Optional<String> name) {
//        final String value = String.format(template, name.orElse(defaultName));
//        return new Saying(counter.incrementAndGet(), value);
//    }

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    private final Template template;
    private final AtomicLong counter;

    public HelloWorldResource(Template template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed(name = "get-requests-timed")
    @Metered(name = "get-requests-metered")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), template.render(name));
    }

    @POST
    public void receiveHello(Saying saying) {
        LOGGER.info("Received a saying: {}", saying);
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveDate(@QueryParam("date") Optional<DateTimeParam> dateTimeParam) {
        if (dateTimeParam.isPresent()) {
            final DateTimeParam actualDateTimeParam = dateTimeParam.get();
            LOGGER.info("Received a date: {}", actualDateTimeParam);
            return actualDateTimeParam.get().toString();
        } else {
            LOGGER.warn("No received date");
            return null;
        }
    }

    private String message;

    public HelloWorldResource(Template template, AtomicLong counter, String message) {
        this.template = template;
        this.counter = counter;
        this.message = message;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(){
        return message;
    }
}