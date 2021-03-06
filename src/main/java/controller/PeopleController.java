package controller;


import io.dropwizard.hibernate.UnitOfWork;
import model.Person;
import service.PersonService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleController {

    private final PersonService personService;

    public PeopleController(PersonService peopleDAO) {
        this.personService = peopleDAO;
    }

    @POST
    @UnitOfWork
    public Person createPerson(@Valid Person person) {
        return personService.create(person);
    }

    @GET
    @UnitOfWork
    public List<Person> listPeople() {
        return personService.findAll();
    }

}
