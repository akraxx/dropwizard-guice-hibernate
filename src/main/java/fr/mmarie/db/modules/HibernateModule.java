package fr.mmarie.db.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import fr.mmarie.HibernateConfiguration;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Maximilien on 17/10/2014.
 */

public class HibernateModule extends AbstractModule {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateModule.class);

    private final HibernateBundle hibernateBundle;

    public HibernateModule(Bootstrap<HibernateConfiguration> bootstrap, HibernateBundle hibernateBundle) {
        this.hibernateBundle = hibernateBundle;
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    protected void configure() {

    }

    @Provides
    public SessionFactory provideSessionFactory(HibernateConfiguration configuration,
                                                Environment environment) {
        return hibernateBundle.getSessionFactory();
    }
}
