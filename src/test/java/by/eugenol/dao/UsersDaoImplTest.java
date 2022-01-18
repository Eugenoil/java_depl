package by.eugenol.dao;


import by.eugenol.dao.test_session_factory.TestSessionFactory;
import by.eugenol.interfaces.UsersDao;
import by.eugenol.pojos.Roles;
import by.eugenol.pojos.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsersDaoImplTest{

    private final SessionFactory sessionFactory = TestSessionFactory.getSessionFactory();

    @Test
    void findById() throws SQLException {
        //Given
        Set<Roles> roles = new HashSet<>();
        UsersDao<Users, Integer> usersDao = new UsersDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("admin");
        role_manager.setName("manager");
        roles.add(role_admin);
        roles.add(role_manager);
        Users expectedUser = new Users();;
        expectedUser.setLogin("Tom");
        expectedUser.setRoles(roles);
        usersDao.save(expectedUser);

        //When
        Users actualUser = usersDao.getUsersById(expectedUser.getId());

        //Then
        assertEquals(actualUser.toString(), expectedUser.toString());

        usersDao.deleteUserById(expectedUser.getId());
    }

    @Test
    void save() throws SQLException {
        //Given
        Set<Roles> roles = new HashSet<>();
        UsersDao<Users, Integer> usersDao = new UsersDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("admin");
        role_manager.setName("manager");
        roles.add(role_admin);
        roles.add(role_manager);

        Users expectedUser = new Users();
        expectedUser.setLogin("Tom");
        expectedUser.setRoles(roles);

        //When
        Serializable id = usersDao.save(expectedUser);


        //Then
        Session session = sessionFactory.openSession();
        assertNotNull(id);
        assertNotNull(session.get(Users.class, id));
        assertEquals(expectedUser.getLogin(), session.get(Users.class, expectedUser.getId()).getLogin());
        assertEquals(expectedUser.getRoles(), session.get(Users.class, expectedUser.getId()).getRoles());

        session.close();

        usersDao.deleteUserById(expectedUser.getId());
    }

    @Test
    void update() throws SQLException {
        //Given
        Session session = sessionFactory.openSession();
        Set<Roles> roles = new HashSet<>();
        UsersDao<Users, Integer> usersDao = new UsersDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("admin");
        role_manager.setName("manager");
        roles.add(role_admin);
        roles.add(role_manager);

        Users expectedUser = new Users();
        expectedUser.setLogin("Tom");
        expectedUser.setRoles(roles);
        usersDao.save(expectedUser);

        //When
        expectedUser.setLogin("Mike");
        usersDao.update(expectedUser);

        //Then
        assertEquals(expectedUser.getLogin(), "Mike");

        usersDao.deleteUserById(expectedUser.getId());
    }

    @Test
    void deleteUserById() throws SQLException {
        //Given
        Set<Roles> roles = new HashSet<>();
        UsersDao<Users, Integer> usersDao = new UsersDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("admin");
        role_manager.setName("manager");
        roles.add(role_admin);
        roles.add(role_manager);

        Users expectedUser = new Users();
        expectedUser.setLogin("Tom");
        expectedUser.setRoles(roles);
        usersDao.save(expectedUser);

        //When
        boolean isDeleted = usersDao.deleteUserById(expectedUser.getId());

        //Then
        assertTrue(isDeleted);
    }

    @Test
    void findAll() throws SQLException {
        //Given
        Set<Roles> roles = new HashSet<>();
        UsersDao<Users, Integer> usersDao = new UsersDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("admin");
        role_manager.setName("manager");
        roles.add(role_admin);
        roles.add(role_manager);

        Users firstUser = new Users();
        firstUser.setLogin("Tom");
        firstUser.setRoles(roles);
        Users secondUser = new Users();
        secondUser.setLogin("Mike");
        secondUser.setRoles(roles);

        usersDao.save(firstUser);
        usersDao.save(secondUser);

        List<Users> expectedUserList = new ArrayList<>();
        expectedUserList.add(firstUser);
        expectedUserList.add(secondUser);

        //When
        List<Users> actualUserList = usersDao.findAll();

        //Then
        assertFalse(actualUserList.isEmpty());
        assertEquals(actualUserList.toString(), expectedUserList.toString());

        usersDao.deleteUserById(firstUser.getId());
        usersDao.deleteUserById(secondUser.getId());
    }
}