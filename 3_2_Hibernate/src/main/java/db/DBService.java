package db;

import accounts.UserProfile;
import db.dao.UsersDAO;
import db.dataset.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.PersistenceException;

public class DBService {

    private final SessionFactory sessionFactory;

    public DBService() {

        Configuration cfg = getH2Configuration();
        sessionFactory = createSessionFactory(cfg);

    }

    public UserProfile getUser(String login) {

        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            UsersDataSet dataSet = dao.getUser(login);
            return mapDataSetToProfile(dataSet);
        } catch (PersistenceException e) {
            return null;
        }

    }

    public long addUser(String name, String password) {

        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            Transaction transaction = session.beginTransaction();
            long id = dao.insertUser(name, password);
            transaction.commit();
            return id;
        }

    }

    private UserProfile mapDataSetToProfile(UsersDataSet usersDataSet) {

        if (usersDataSet == null) {
            return null;
        }

        return new UserProfile(usersDataSet.getLogin(), usersDataSet.getPassword(), "");

    }

    private SessionFactory createSessionFactory(Configuration cfg) {

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(cfg.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return cfg.buildSessionFactory(serviceRegistry);

    }

    private Configuration getH2Configuration() {

        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(UsersDataSet.class);

        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        cfg.setProperty("hibernate.connection.driver.class", "org.h2.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        cfg.setProperty("hibernate.connection.username", "test");
        cfg.setProperty("hibernate.connection.password", "test");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");

        return cfg;
    }
}
