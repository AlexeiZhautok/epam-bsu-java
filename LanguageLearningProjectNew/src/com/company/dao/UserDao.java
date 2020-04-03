package com.company.dao;

import com.company.model.User;
import com.company.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

    public void createUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination, true))) {
            writer.write(user.toStringFileFormat());
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
                    createUser(userIter);
                }
            }
        } catch (IOException e) {
            log.fatal(e);
        }
    }

    public void UpdateById (long inputId, User newUser) {
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
            e.printStackTrace();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_Destination))) {
            for (User userIter : users) {
                createUser(userIter);
            }
        } catch (IOException e) {
            log.fatal(e);
        }
    }
}
