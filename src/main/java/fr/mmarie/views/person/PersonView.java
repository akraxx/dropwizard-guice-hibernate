package fr.mmarie.views.person;

import fr.mmarie.core.Person;
import io.dropwizard.views.View;
import lombok.Getter;

import java.nio.charset.Charset;

/**
 * Created by Maximilien on 18/10/2014.
 */
@Getter
public class PersonView extends View {
    public static String TEMPLATE = "/views/person/view.ftl";

    private Person person;

    public PersonView(String templateName, Charset charset) {
        super(templateName, charset);
    }

    public PersonView(Person person) {
        this(TEMPLATE, Charset.forName("UTF-8"));
        this.person = person;
    }
}
