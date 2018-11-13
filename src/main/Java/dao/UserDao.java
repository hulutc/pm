/*
 * tiancheng copyrights reserved
 */

package dao;

import PM.Skill;
import model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public static User select(String username){
        JdbcTemplate jdbc = Dao.get_jdbc();
        System.out.println("nihao");
        String sql = String.format("select * from user where username='%s'", username);
        System.out.println(sql);
        List list = jdbc.query(sql, new RowMapper() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet);
                return user;
            }
        });
        if(list.size()==0){
            return null;
        }
        return (User)list.get(0);
    }
}
