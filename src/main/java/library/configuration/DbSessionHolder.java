package library.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

import library.entities.Author;
import library.entities.Book;
import library.entities.Review;

import static java.util.Arrays.asList;

public class DbSessionHolder {

    private static final List<Class> ENTITIES = asList(Author.class, Book.class, Review.class);

    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private static final String DB_NAME = "library";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useSSL=false&serverTimezone=UTC";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";

    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String HBM2DDL_AUTO = "validate";

    private static SessionFactory sessionFactory;

    private DbSessionHolder() {
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void shutdown() {
        // Close caches and connection pools
        if(getInstance() != null) getInstance().close();
    }

    //https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    private static class LazyHolder {
        static final SessionFactory INSTANCE = initializeSessionFactory();
    }

    private static SessionFactory initializeSessionFactory() {
        try {
            Configuration configuration = configuration(properties());
            sessionFactory = configuration.buildSessionFactory(serviceRegistry(configuration));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    private static Configuration configuration(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        ENTITIES.forEach(configuration::addAnnotatedClass);
        return configuration;
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DB_DRIVER);
        properties.put(Environment.URL, DB_URL);
        properties.put(Environment.USER, DB_USERNAME);
        properties.put(Environment.PASS, DB_PASSWORD);
        properties.put(Environment.DIALECT, DB_DIALECT);
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);
        return properties;
    }

    private static ServiceRegistry serviceRegistry(Configuration configuration) {
        return new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
    }
}
