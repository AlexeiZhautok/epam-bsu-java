package com.company.dbdao;

import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DatabaseDao<E> {
    private final String user = "JAVA_EPAM";
    private final String password = "oracle";

    public static Logger log = LogManager.getLogger();
    private Connection connection;
    protected Statement statement;
    protected ResultSet response;

    protected String table;

    public DatabaseDao() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", user, password);
        } catch (SQLException e) {
            log.fatal(e);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e){
            log.fatal(e);
        }
    }

    public List<E> getAll() {
        String query = "SELECT * FROM " + table;
        List<E> list = new ArrayList<>();
        try {
            response = statement.executeQuery(query);
            while (response.next()) {
                list.add(parseModel(response));
            }
        } catch (SQLException e) {
            log.fatal(e);
        }
        return list;
    }

    public void updateByID(E obj) {
        try {
            statement.executeQuery(makeUpdateByIDQuery(obj));
        } catch (SQLException e) {
            log.fatal(e);
        }
    }

    public void insert(E obj) {
        try {
            statement.executeQuery(makeInsertQuery(obj));
        } catch (SQLException e) {
            log.fatal(e);
        }
    }

    public void deleteByID(long id) {
        try {
            statement.executeQuery(makeDeleteByIDQuery(id));
        } catch (SQLException e) {
            log.fatal(e);
        }
    }

    public E getByID(long id) {
        try {
            response = statement.executeQuery(makeGetByIDQuery(id));
            if(response.next()) {
                return parseModel(response);
            }
        } catch (SQLException e) {
            log.fatal(e);
        }
        return null;
    }

    public long getLastID() {
        long id = 0;
        try {
            response = statement.executeQuery(makeGetLastIDQuery());
            if(response.next()) {
                id = response.getLong("user_id");
            }
        } catch (SQLException e) {
            log.fatal(e);
        }
        return id;
    }

    public abstract E parseModel(ResultSet response);
    public abstract String makeUpdateByIDQuery(E obj);
    public abstract String makeInsertQuery(E obj);
    public abstract String makeDeleteByIDQuery(long id);
    public abstract String makeGetByIDQuery(long id);
    public abstract String makeGetLastIDQuery();

    public void setTable(String table) {
        this.table = table;
    }
}
