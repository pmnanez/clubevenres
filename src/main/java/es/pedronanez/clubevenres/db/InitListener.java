package es.pedronanez.clubevenres.db;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener for the InitEvent of the server. Starts the database on the init
 * event of the server and stops it on stop event.
 *
 * @see InitEvent
 */
public class InitListener implements ServletContextListener {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(InitListener.class
            .getName());

    /**
     * Starts the database and the service that will check the expiration date
     * of Callbacks.
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     *      .ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("Starting database connection initialization");
        Database.initInstance();
        LOGGER.info("Database connection initialized");
    }

    /**
     * Stops everything.
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     *      ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("Destroying database connection instance");
        Database.closeConnection();
        LOGGER.info("Database connection instance destroyed");
    }

}
