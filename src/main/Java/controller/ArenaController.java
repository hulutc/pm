/*
 * tiancheng copyrights reserved
 */

package controller;

import Scene.Arena;
import Scene.GrassArena;
import dao.PMDao;
import dao.RoleDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import PM.PM;
import PM.Property;
import Role.Role;
import login.Login;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static util.PMUtil.PMMessage.TOKEN_INVALID;

@Controller
@RequestMapping("arena")
public class ArenaController {
    public HashMap<String, Arena> arenas = new HashMap<String, Arena>();

    private class Hulu {
        public String name = "tiancheng";
        public String gender = "male";
//        public String getName() {
//            return name;
//        }


        @Override
        public String toString() {
            return name + " " + gender;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/hulu", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Hulu hulu() {
        Hulu hl = new Hulu();
        return hl;
    }

    @ResponseBody
    @RequestMapping(value = "/role", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Role get_role_info() {
        Role role = new Role();
//        for(int i=0; i<5; i++){
//            role.add_pm(new PM());
//        }
        return role;
    }

    public PM encounter(Role role) {
        PM pm = new PM();
        System.out.println(role.toString() + "  encountered  \n" + pm.toString() + "\nstart fighting!");
        return pm;
    }

    @ResponseBody
    @RequestMapping(value = "/wander", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Role wander() {
        Role role = new Role();
//        for(int i=0; i<5; i++){
//            role.add_pm(new PM());
//        }
        return role;
    }

    @ResponseBody
    @RequestMapping(value = "/walk", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public String walk(@RequestParam(required = false) Integer steps, String token) {
        if (!validate(token)) return TOKEN_INVALID;
        double ratio = 0.6;
        Role role = get_role(token);
        String ret = "没有遇到精灵";
        if (Math.random() < ratio) {
            PM pm = encounter(role);
            GrassArena arena = new GrassArena(role, pm);
            System.out.println("nihao: " + arena);
            arenas.put(token, arena);
            ret = role.toString() + "  encountered  \n" + pm.toString() + "\nstart fighting!";
            ret += "\n";
            return ret;
        }
//        for(int i=0; i<5; i++){
//            role.add_pm(new PM());
//        }

        return ret;
    }


    @ResponseBody
    @RequestMapping(value = "/attack", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String attack(String token, int skill_num) {
        if (!validate(token)) return TOKEN_INVALID;

        System.out.println("keys: " + arenas.keySet());
        if (arenas.containsKey(token)) {
            System.out.println("user exists");
            Arena arena = arenas.get(token);
            return arena.attack(1, skill_num);
        }
        return "no arena exists";
    }


    @ResponseBody
    @RequestMapping(value = "/get_self", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String get_self(@RequestParam String token) {
        if (!validate(token)) return TOKEN_INVALID;
        Arena arena = arenas.get(token);
        System.out.println("self: " + arena);
        return arena.stage_pm1.toString();
    }


    public boolean validate(String token) {
        if (token.equals(Login.TOKEN)) {
            System.out.println("success, token valid");
            return true;
        } else {
            System.out.println("fail, invalid token");
            return false;
        }
    }


    public Role get_role(String token) {
        if (token.equals(Login.TOKEN)) {
            return new Role();
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/gtest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object test() {
        PM pm = PMDao.select(15);
        System.out.println(pm);
        return pm;
    }
}
