package fr.mmarie;

import com.google.inject.Stage;
import com.hubspot.dropwizard.guice.GuiceBundle;
import fr.mmarie.core.Department;
import fr.mmarie.core.Person;
import fr.mmarie.db.modules.HibernateModule;
import fr.mmarie.instrumentation.InstrumentationModule;
import fr.mmarie.tasks.LoggingTask;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Maximilien on 16/10/2014.
 */
public class HibernateApplication extends Application<HibernateConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateApplication.class);

    private final HibernateBundle<HibernateConfiguration> hibernateBundle =
            new HibernateBundle<HibernateConfiguration>(Person.class, Department.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(HibernateConfiguration configuration) {
                    return configuration.getDatabase();
                }
            };

    public static void main(String[] args) throws Exception {
        // First, initialize the database
        new HibernateApplication().run(new String[]{"db", "migrate", "properties.yml"});

        // Then run the jetty server
        new HibernateApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HibernateConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<HibernateConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(HibernateConfiguration configuration) {
                return configuration.getDatabase();
            }
        });

        // Guice everywhere!!!!
        GuiceBundle<HibernateConfiguration> guiceBundle = GuiceBundle.<HibernateConfiguration>newBuilder()
                .addModule(new HibernateModule(bootstrap, hibernateBundle))
                .addModule(new InstrumentationModule(bootstrap.getMetricRegistry()))
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(HibernateConfiguration.class)
                .build();

        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(HibernateConfiguration configuration, Environment environment) throws Exception {
        LOGGER.info("Run dropwizard");
        // Change the logging level at runtime
        environment.admin().addTask(new LoggingTask());
    }
}
