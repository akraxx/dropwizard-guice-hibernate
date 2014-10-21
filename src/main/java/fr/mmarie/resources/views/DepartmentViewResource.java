package fr.mmarie.resources.views;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import fr.mmarie.core.Department;
import fr.mmarie.services.DepartmentService;
import fr.mmarie.views.department.DepartmentView;
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
@Path("/department")
public class DepartmentViewResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentViewResource.class);

    private DepartmentService departmentService;

    @Inject
    public DepartmentViewResource(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GET
    @Timed
    @Path("{departmentId}")
    @UnitOfWork
    @Produces(MediaType.TEXT_HTML+"; charset=UTF-8")
    public DepartmentView getDepartmentView(@PathParam("departmentId") LongParam departmentId) {
        Department department = departmentService.getDepartment(departmentId);
//        LOGGER.info("Persons [{}]", department.getPersons());
        return new DepartmentView(department);
    }
}
