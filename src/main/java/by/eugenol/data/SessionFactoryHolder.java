package by.eugenol.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.lang.module.Configuration;


public class SessionFactoryHolder {

    private static SessionFactory sessionFactory;

    /**
     * Method to get session and database configuration
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            StandardServiceRegistry reg =
                    new StandardServiceRegistryBuilder()
                            .configure("hibernate.department.cfg.xml")
                            .build();

            sessionFactory = new MetadataSources(reg)
                    .buildMetadata()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}
