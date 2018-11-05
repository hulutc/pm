/*
 * tiancheng copyrights reserved
 */

package dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }


    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public boolean check(String name, String passwod) {
        System.out.println(jdbcTemplate);
//        String sql = "get count(id) from u2 where name=? and password=?";
//        int i = jdbcTemplate.queryForInt(sql, new Object[]{name, passwod});
//        if (i > 0) {
//            return true;
//        } else {
//            return false;
//        }
        return false;
    }


    public void addUser(String name, String password) {
        String sql = "insert into u2 (name,password) values(?,?)";
        jdbcTemplate.update(sql, new Object[]{name, password});
    }


    public boolean unit(String name) {
//        String sql = "select count(id) from u2 where name=?";
//        int rs = jdbcTemplate.queryForInt(sql, new Object[]{name});
//        if (rs > 0) {
//            return true;
//        }
        return false;
    }

    public static void main(String[] args) {
        RoleDao rd = new RoleDao();
        rd.check("zhang", "xiang");

    }
}
