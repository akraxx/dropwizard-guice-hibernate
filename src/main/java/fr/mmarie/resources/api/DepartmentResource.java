package fr.mmarie.resources.api;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import fr.mmarie.core.Department;
import fr.mmarie.services.DepartmentService;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Maximilien on 16/10/2014.
 */
@Path("/api/department")
public class DepartmentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentResource.class);

    private DepartmentService departmentService;

    @Inject
    public DepartmentResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GET
    @Timed
    @Path("{departmentId}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Department getPerson(@PathParam("departmentId") LongParam departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        LOGGER.info("Department has been found [{}], with persons [{}]", department, department.getPersons());
        return department;
    }
}
