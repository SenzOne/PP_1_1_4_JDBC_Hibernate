package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;


public class Util {
    private static final String PASSWORD = "AEZAkmi101";
    private static final String USERNAME = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/JDBC_Hibernate";


    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        configuration.setProperty(Environment.URL, URL);
        configuration.setProperty(Environment.USER, USERNAME);
        configuration.setProperty(Environment.PASS, PASSWORD);
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");


        configuration.setProperty(Environment.SHOW_SQL, "true");
        configuration.setProperty(Environment.FORMAT_SQL, "true");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

}

