package by.eugenol.servlet;

import by.eugenol.dao.UsersDaoImpl;
import by.eugenol.interfaces.UsersDao;
import by.eugenol.pojos.Roles;
import by.eugenol.pojos.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UsersDao userDao;

    public void init() {
        userDao = new UsersDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException | IOException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Get List of users for creating view on JSP
     */
    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Users> listUser = userDao.findAll();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Forward to new input form
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * Forward to edit form
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Users existingUser = userDao.getUsersById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }


    /**
     * Get login and role, and insert user to database
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String login = request.getParameter("login");
        String role = request.getParameter("roleadd");
        Roles roles = new Roles();
        roles.setName(role);
        Set<Roles> rolesSet = new HashSet<>();
        rolesSet.add(roles);
        Users newUser = new Users(login);
        userDao.saveUserWithRoles(rolesSet, newUser);
        response.sendRedirect("list");
    }


    /**
     * Get id, and roles from JSP and update user in database.
     * Method received roles for delete and for add.
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Users user = userDao.getUsersById(id);
        String roleToAdd = request.getParameter("roleadd");
        String roleToDelete = request.getParameter("roledelete");
        Roles rolesToAdd = new Roles();
        Roles rolesToDelete = new Roles();
        rolesToAdd.setName(roleToAdd);
        rolesToDelete.setName(roleToDelete);
        Set<Roles> existingRoles = user.getRoles();
        existingRoles.add(rolesToAdd);
        existingRoles.remove(rolesToDelete);
        user.setRoles(existingRoles);
        userDao.update(user);
        response.sendRedirect("list");
    }


    /**
     * Get id from JSP and delete user by id using DAO
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUserById(id);
        response.sendRedirect("list");
    }


}

