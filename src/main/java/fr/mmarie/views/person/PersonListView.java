package fr.mmarie.views.person;

import fr.mmarie.core.Person;
import io.dropwizard.views.View;
import lombok.Getter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Maximilien on 18/10/2014.
 */
@Getter
public class PersonListView extends View {
    public static String TEMPLATE = "/views/person/list.ftl";

    private List<Person> persons;

    public PersonListView(String templateName, Charset charset) {
        super(templateName, charset);
    }

    public PersonListView(List<Person> persons) {
        this(TEMPLATE, Charset.forName("UTF-8"));
        this.persons = persons;
    }
}
