/*
 * tiancheng copyrights reserved
 */

package dao;

import PM.PM;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PMDao {
    public static int get_length() {
        JdbcTemplate jdbc = Dao.get_jdbc();
        String sql = "select count(*) as length from pm";
        System.out.println(sql);
        List list = jdbc.query(sql, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println(resultSet);
//                print(resultSet)
//                PM pm = new PM(resultSet);
                return resultSet.getInt("length");
            }
        });
        return (int) list.get(0);
    }

    public static PM get() {
        JdbcTemplate jdbc = Dao.get_jdbc();
        int id = (int)(Math.random()*get_length()) + 1;
        String sql = String.format("select * from pm where id=%d", id);
        System.out.println(sql);
        List list = jdbc.query(sql, new RowMapper() {
            @Override
            public PM mapRow(ResultSet resultSet, int i) throws SQLException {
                PM pm = new PM(resultSet);
                return pm;
            }
        });
        if(list.size()==0){
            return null;
        }
        return (PM) list.get(0);
    }

    public static PM get(int id) {
        JdbcTemplate jdbc = Dao.get_jdbc();
        String sql = String.format("select * from pm where id=%d", id);
        System.out.println(sql);
        List list = jdbc.query(sql, new RowMapper() {
            @Override
            public PM mapRow(ResultSet resultSet, int i) throws SQLException {
                PM pm = new PM(resultSet);
                return pm;
            }
        });
        if(list.size()==0){
            return null;
        }
        return (PM) list.get(0);
    }
}
