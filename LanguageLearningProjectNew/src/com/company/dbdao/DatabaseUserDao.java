package com.company.dbdao;

import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUserDao extends DatabaseDao<User> {
    {
        setTable("Users");
    }

    @Override
    public User parseModel(ResultSet response) {
        try {
            long id = response.getLong("user_id");
            String login = response.getString("user_login").trim();
            String password = response.getString("user_password").trim();
            String email = response.getString("user_email").trim();
            String roleString = response.getString("user_role").trim();
            UserRole role = UserRole.valueOf(roleString);
            return new User(id, login, password, email, role);
        } catch (SQLException e) {
            log.fatal(e);
        }
        return null;
    }

    public String makeUpdateByIDQuery(User user) {
        String query = "UPDATE USERS ";
        query += "SET USER_LOGIN = " + "'" + user.getLogin() + "'" + ",";
        query += "USER_PASSWORD = " + "'" + user.getPassword() + "'" + ",";
        query += "USER_EMAIL = " + "'" + user.getEmail() + "'" + ",";
        query += "USER_ROLE = " + "'" + user.getRole().toString() + "'" + " ";
        query += "WHERE USER_ID = " + user.getId();
        return query;
    }

    public String makeInsertQuery(User user) {
        String query = "INSERT INTO USERS ";
        query += "(USER_ID, USER_LOGIN, USER_PASSWORD, USER_EMAIL, USER_ROLE)";
        query += "VALUES ";
        query += "(";
        query += user.getId() + ", ";
        query += "'" + user.getLogin() + "'" + ", ";
        query += "'" + user.getPassword() + "'" + ", ";
        query += "'" + user.getEmail() + "'" + ", ";
        query += "'" + user.getRole().toString() + "'";
        query += ")";
        return query;
    }

    public User getByLogin(String login) {
        String query = "SELECT * FROM USERS WHERE USER_LOGIN = " + login;
        try {
            response = statement.executeQuery(query);
            if(response.next()) {
                return parseModel(response);
            }
        } catch (SQLException e) {
            log.fatal(e);
        }
        return null;
    }

//    DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
    public String makeDeleteByIDQuery(long id) {
        String query = "DELETE FROM USERS WHERE USER_ID = " + id;
        return query;
    }

    public String makeGetByIDQuery(long id) {
        String query = "SELECT * FROM USERS WHERE USER_ID = " + id;
        return query;
    }

    public String makeGetLastIDQuery() {
        String query = "SELECT MAX(USER_ID) FROM USERS";
        return query;
    }
}
