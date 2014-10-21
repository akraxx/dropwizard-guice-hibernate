package fr.mmarie.db.dao;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.mmarie.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Maximilien on 16/10/2014.
 */
@Singleton
public class PersonDAO extends AbstractDAO<Person> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    @Inject
    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Timed(absolute = true)
    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    @Timed(name = "findAllExplicit")
    public List<Person> findAll() {
        return list(namedQuery("fr.mmarie.core.Person.findAll"));
    }

    @Timed(absolute = true, name = "findByDepartmentIdExplicit")
    public List<Person> findByDepartmentId(long departmentId) {
        return list(namedQuery("fr.mmarie.core.Person.findByDepartmentId").setParameter("departmentId", departmentId));
    }

}
