package by.eugenol.dao;

import by.eugenol.dao.test_session_factory.TestSessionFactory;
import by.eugenol.interfaces.RolesDao;
import by.eugenol.pojos.Roles;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoleDaoImplTest {

   /* private final SessionFactory sessionFactory = TestSessionFactory.getSessionFactory();

    @Test
    void findById() throws SQLException {
        RolesDao<Roles, Integer> rolesDao = new RolesDaoImpl(sessionFactory);
        Roles expectedRole = new Roles();
        expectedRole.setName("Admin");

        //When
        rolesDao.save(expectedRole);
        Roles actualRole = rolesDao.getRolesById(expectedRole.getId());

        //Then
        assertEquals(actualRole, expectedRole);

        rolesDao.deleteRoleById(expectedRole.getId());
    }

    @Test
    void save() throws SQLException {
        //Given
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        RolesDao roleDAO = new RolesDaoImpl(sessionFactory);
        role_admin.setName("Admin");
        role_manager.setName("Manager");


        //When
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(role_admin);
        rolesList.add(role_manager);
        roleDAO.save(role_admin);
        roleDAO.save(role_manager);
        List<Roles> roleDAOlist = roleDAO.findAll();

        //Then
        assertEquals(rolesList.toString(), roleDAOlist.toString());
        roleDAO.deleteRoleById(role_admin.getId());
        roleDAO.deleteRoleById(role_manager.getId());
    }

    @Test
    void update() throws SQLException {
        //Given
        RolesDao rolesDao = new RolesDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        role_admin.setName("Manager");
        rolesDao.save(role_admin);
        role_admin.setName("Admin");

        //When
        rolesDao.update(role_admin);

        //Then
        assertEquals("Admin", rolesDao.getRolesById(Math.toIntExact(role_admin.getId())).getName());
        rolesDao.deleteRoleById(role_admin.getId());
    }

    @Test
    void deleteRoleById() throws SQLException {
        //Given
        RolesDao rolesDao = new RolesDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        role_admin.setName("Admin");

        //When
        rolesDao.save(role_admin);
        boolean isDeleted = rolesDao.deleteRoleById(role_admin.getId());

        //Then
        List<Roles> actualResult = rolesDao.findAll();
        assertTrue(isDeleted);
        assertTrue(actualResult.isEmpty());
        rolesDao.deleteRoleById(role_admin.getId());
    }


    @Test
    void findAllList() throws SQLException {
        //Given
        RolesDao roleDAO = new RolesDaoImpl(sessionFactory);
        Roles role_admin = new Roles();
        Roles role_manager = new Roles();
        role_admin.setName("Admin");
        role_manager.setName("Manager");
        List<Roles> expectedResult = new ArrayList<>();
        expectedResult.add(role_admin);
        expectedResult.add(role_manager);

        //When
        roleDAO.save(role_admin);
        roleDAO.save(role_manager);
        List<Roles> actualResult = roleDAO.findAll();

        //Then

        assertEquals(expectedResult, actualResult);
        roleDAO.deleteRoleById(role_admin.getId());
        roleDAO.deleteRoleById(role_manager.getId());
    }
*/
}