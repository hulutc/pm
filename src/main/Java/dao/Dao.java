/*
 * tiancheng copyrights reserved
 */

package dao;

import PM.PM;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Dao {
    public static JdbcTemplate jdbc;
    public String table = "pm";

    public Dao(){
    }

    public Dao(String table){
        this.table = table;
    }

    public static JdbcTemplate get_jdbc(){
        if (jdbc == null){
            ApplicationContext ctx = new ClassPathXmlApplicationContext("mvc-dispatcher.xml");
            DataSource dataSource = (DataSource) ctx.getBean("dataSource");
            jdbc = new JdbcTemplate(dataSource);
        }
        return jdbc;
    }


}
