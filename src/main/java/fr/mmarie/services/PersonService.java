package fr.mmarie.services;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.api.NotFoundException;
import fr.mmarie.core.Person;
import fr.mmarie.db.dao.PersonDAO;
import io.dropwizard.jersey.params.LongParam;
import io.dropwizard.lifecycle.Managed;

import java.util.List;

/**
 * Created by Maximilien on 17/10/2014.
 */
@Singleton
public class PersonService {

    private PersonDAO personDAO;

    public Person findSafely(long personId) {
        final Optional<Person> person = personDAO.findById(personId);
        if (!person.isPresent()) {
            throw new NotFoundException("No such user.");
        }
        return person.get();
    }

    @Inject
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Timed
    public Person getPerson(LongParam personId) {
        return findSafely(personId.get());
    }

    public List<Person> getPersons() {
        return personDAO.findAll();
    }

    public List<Person> getPersonsByDepartmentId(LongParam departmentId) {
        return personDAO.findByDepartmentId(departmentId.get());
    }
}
