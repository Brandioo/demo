package controller;

import api.Saying;
import io.dropwizard.jersey.params.DateTimeParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private String message;
//    private final Template template;
//    private final AtomicLong counter;
//
//    public HelloWorldResource(Template template) {
//        this.template = template;
//        this.counter = new AtomicLong();
//    }

    public HelloController(String message) {
        this.message = message;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(){
        return message;
    }

//    @GET
//    public String getGreeting(@PathParam("name") String name) {
//        return "HelloWorld" + name;
//    }

//    @GET
//    @Timed(name = "get-requests-timed")
//    @Metered(name = "get-requests-metered")
//    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
//    public Saying sayHello(@QueryParam("name") Optional<String> name) {
//        return new Saying(counter.incrementAndGet(), template.render(name));
//    }

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
}
