package fr.mmarie.views.department;

import fr.mmarie.core.Department;
import fr.mmarie.core.Person;
import io.dropwizard.views.View;
import lombok.Getter;

import java.nio.charset.Charset;

/**
 * Created by Maximilien on 18/10/2014.
 */
@Getter
public class DepartmentView extends View {
    public static String TEMPLATE = "/views/department/view.ftl";

    private Department department;

    public DepartmentView(String templateName, Charset charset) {
        super(templateName, charset);
    }

    public DepartmentView(Department department) {
        this(TEMPLATE, Charset.forName("UTF-8"));
        this.department = department;
    }
}
