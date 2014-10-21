package fr.mmarie.resources.api;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import fr.mmarie.core.Person;
import fr.mmarie.services.PersonService;
import fr.mmarie.views.person.PersonView;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Maximilien on 16/10/2014.
 */
@Path("/api/person")
public class PersonResource {
    private PersonService personService;

    @Inject
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Timed
    @Path("{personId}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPerson(@PathParam("personId") LongParam personId) {
        return personService.getPerson(personId);
    }

    @GET
    @Timed
    @Path("/byDepartmentId/{departmentId}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
    public List<Person> getPersonsByDepartmentId(@PathParam("departmentId") LongParam departmentId) {
        return personService.getPersonsByDepartmentId(departmentId);
    }

    @GET
    @Timed(absolute = false, name = "getAllPersons")
    @Path("all")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON+"; charset=UTF-8")
    public List<Person> getPersons() {
        return personService.getPersons();
    }
}
