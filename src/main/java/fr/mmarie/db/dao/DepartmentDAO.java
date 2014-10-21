package fr.mmarie.db.dao;

import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.mmarie.core.Department;
import fr.mmarie.core.Person;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by Maximilien on 17/10/2014.
 */
@Singleton
public class DepartmentDAO extends AbstractDAO<Department> {


    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    @Inject
    public DepartmentDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<Department> findById(Long id) {
        return Optional.fromNullable(get(id));
    }
}
