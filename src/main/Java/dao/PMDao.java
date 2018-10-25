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
    public static PM select(int id){
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
        System.out.println(list);
        return (PM)list.get(0);
    }
}
