/*
 * tiancheng copyrights reserved
 */

package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public int id;
    public String username;
    public String password;
    public String salt;

    public User(ResultSet resultSet) {
        try {
            id = resultSet.getInt("id");
            username = resultSet.getString("username");
            password = resultSet.getString("password");
            salt = resultSet.getString("salt");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
