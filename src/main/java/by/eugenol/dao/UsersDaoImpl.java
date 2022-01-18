package by.eugenol.dao;

import by.eugenol.data.SessionFactoryHolder;
import by.eugenol.interfaces.UsersDao;
import by.eugenol.pojos.Roles;
import by.eugenol.pojos.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class UsersDaoImpl implements UsersDao<Users, Integer> {

    private final SessionFactory sessionFactory;

    public UsersDaoImpl() {
        this.sessionFactory = SessionFactoryHolder.getSessionFactory();
    }

    public UsersDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * id Get User by id with method get
     */
    @Override
    public Users getUsersById(Integer id) {
        Session session = sessionFactory.openSession();
        Users users = session.get(Users.class, id);
        session.close();
        return users;
    }

    /**
     * Get list of all Users
     */
    @Override
    public List<Users> findAll() { //
        List<Users> users = (List<Users>) sessionFactory
                .openSession()
                .createQuery("From Users").list();
        return users;
    }

    /**
     * Save user and get id of saved user
     */
    @Override
    public Serializable save(Users user) {
        Session session = sessionFactory.openSession();
        Serializable id = null;
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            id = session.save(user);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return id;
    }

    /**
     * Update existed user in database
     *
     * @throws SQLException
     */
    @Override
    public void update(Users users) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(users);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
    }

    /**
     * Delete user from database with id
     */
    @Override
    public boolean deleteUserById(Serializable id) {
        Session session = sessionFactory.openSession();
        Users roles = session.get(Users.class, id);
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.delete(roles);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return roles != null;
    }

    /**
     * Save user with rolesSet set
     */
    @Override
    public Serializable saveUserWithRoles(Set<Roles> rolesSet, Users user) {
        Session session = SessionFactoryHolder.getSessionFactory().openSession();
        Serializable id = null;
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            user.setRoles(rolesSet);
            id = session.save(user);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) tr.rollback();
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return id;
    }
}
