package by.eugenol.interfaces;

import by.eugenol.pojos.Users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UsersDao<T, ID> {

    Users find(Integer id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean save(T o) throws  SQLException;

    boolean update(T o) throws SQLException;

    boolean delete (T o) throws SQLException;

}