package com.company.dao;

import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicReference;

public class UserDao {
    public static String delim = "-";
    private String DB_Destination = "database/users/userDatabase.txt";

    public static Logger log = LogManager.getLogger();

    private User parseUser(String input) {
        StringTokenizer st = new StringTokenizer(input, delim);
        long id = Long.parseLong(st.nextToken());
        String login = st.nextToken();
        String password = st.nextToken();
        String email = st.nextToken();
        UserRole role = UserRole.valueOf(st.nextToken());
        return new User(id, login, password, email, role);
    }

    public long getLastID() {
        long toReturn = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                toReturn = parseUser(tempLine).getId();
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        return toReturn;
    }

    public List<User> getAll() {
        List<User> toReturn = new ArrayList<User>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                toReturn.add(parseUser(tempLine));
            }
            return toReturn;
        } catch (IOException e) {
            log.fatal(e);
        }
        return new ArrayList<User>();
    }

    public User getById(long inputId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                User temp = parseUser(tempLine);
                if (temp.getId() == inputId) {
                    return temp;
                }
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        return null;
    }

    public User getByLogin(String inputLogin) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                User temp = parseUser(tempLine);
                if (temp.getLogin().equals(inputLogin)) {
                    return temp;
                }
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        return null;
    }

    public void createUser(String login, String password, String email, UserRole role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination, true))) {
            User newUser = new User(getLastID() + 1, login, password, email, role);
            writer.write(newUser.toStringFileFormat());
            writer.newLine();
        } catch (IOException ignored) {
        }
    }

    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination))) {
            writer.write("");
        } catch (IOException ignored) {
        }
    }

    public void deleteById(long inputId) {
        List<User> users = getAll();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination))) {
            for (User userIter : users) {
                if (userIter.getId() != inputId) {
                    createUser(userIter.getLogin(), userIter.getPassword(), userIter.getEmail(), userIter.getRole());
                }
            }
        } catch (IOException e) {
            log.fatal(e);
        }
    }

    public void updateById (long inputId, User newUser) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DB_Destination))) {
            String tempLine = "";
            while ((tempLine = reader.readLine()) != null) {
                User temp = parseUser(tempLine);
                if((temp.getId() == inputId)) {
                    users.add(newUser);
                } else {
                    users.add(temp);
                }
            }
        } catch (IOException e) {
            log.fatal(e);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination))) {
            for (User userIter : users) {
                createUser(userIter.getLogin(), userIter.getPassword(), userIter.getEmail(), userIter.getRole());
            }
        } catch (IOException e) {
            log.fatal(e);
        }
    }
}
