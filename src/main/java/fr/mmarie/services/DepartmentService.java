package fr.mmarie.services;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.sun.jersey.api.NotFoundException;
import fr.mmarie.core.Department;
import fr.mmarie.db.dao.DepartmentDAO;
import io.dropwizard.jersey.params.LongParam;

/**
 * Created by Maximilien on 17/10/2014.
 */
@Singleton
public class DepartmentService {

    private DepartmentDAO departmentDAO;

    public Department findSafely(long departmentId) {
        final Optional<Department> department = departmentDAO.findById(departmentId);
        if (!department.isPresent()) {
            throw new NotFoundException("No such department.");
        }
        return department.get();
    }

    @Inject
    public DepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Timed(absolute = true, name = "getDepartment")
    public Department getDepartment(LongParam departmentId) {
        return findSafely(departmentId.get());
    }
}
