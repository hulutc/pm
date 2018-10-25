/*
 * tiancheng copyrights reserved
 */

package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import PM.Skill;

public class SkillDao {

    public static Skill select(int id){
        JdbcTemplate jdbc = Dao.get_jdbc();
        String sql = String.format("select * from skill where id=%d", id);
        System.out.println(sql);
        List list = jdbc.query(sql, new RowMapper() {
            @Override
            public Skill mapRow(ResultSet resultSet, int i) throws SQLException {
                Skill skill = new Skill(resultSet);
                return skill;
            }
        });
        System.out.println(list);
        return (Skill)list.get(0);
    }
}
