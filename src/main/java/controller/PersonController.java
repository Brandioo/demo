package controller;


import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import model.Person;
import service.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService peopleDAO) {
        this.personService = peopleDAO;
    }

    @GET
    @UnitOfWork
    public Person getPerson(@PathParam("personId") LongParam personId) {
        return findSafely(personId.get());
    }

    private Person findSafely(long personId) {
        return personService.findById(personId).orElseThrow(() -> new NotFoundException("No such user."));
    }
}
