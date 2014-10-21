package fr.mmarie.tasks;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;

/**
 * Created by Maximilien on 19/10/2014.
 */
public class LoggingTask extends Task {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggingTask.class);

    /**
     * Create a new task with the given name.
     */
    public LoggingTask() {
        super("logging-level");
    }

    @Override
    public void execute(ImmutableMultimap<String, String> parameters, PrintWriter output) throws Exception {
        output.println("Change logging level");
        try {
            LOGGER.info("parameters [{}]", parameters.toString());
            if(!parameters.containsKey("level")) {
                throw new IllegalArgumentException("You have to specify the logging level");
            }
            Level level = Level.toLevel(parameters.get("level").iterator().next());


            String logger = Logger.ROOT_LOGGER_NAME;
            if(!parameters.containsKey("logger")) {
                output.println("No logger specified, change the root level");
            } else {
                logger = parameters.get("logger").iterator().next();
            }

            Logger root = (Logger) LoggerFactory.getLogger(logger);
            root.setLevel(level);

            output.println("Level of logger [{}], has been changed to [{}]");
        } catch (Exception e) {
            String message = "An error occurred during the change of the logging level : ";
            output.println(message + e.getMessage());
            LOGGER.error(message, e);
        }
    }
}
