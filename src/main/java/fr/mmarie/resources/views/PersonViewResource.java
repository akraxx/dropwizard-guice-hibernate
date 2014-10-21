package fr.mmarie.resources.views;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import fr.mmarie.core.Person;
import fr.mmarie.services.PersonService;
import fr.mmarie.views.person.PersonListView;
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
@Path("/person")
public class PersonViewResource {
    private PersonService personService;

    @Inject
    public PersonViewResource(PersonService personService) {
        this.personService = personService;
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
    @Produces(MediaType.TEXT_HTML+"; charset=UTF-8")
    public PersonListView getPersonsView() {
        return new PersonListView(personService.getPersons());
    }

    @GET
    @Path("/{personId}")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML+"; charset=UTF-8")
    public PersonView getPersonView(@PathParam("personId") LongParam personId) {
        return new PersonView(personService.getPerson(personId));
    }
}
