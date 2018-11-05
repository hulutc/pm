/*
 * tiancheng copyrights reserved
 */

package controller;

import io.vertx.core.json.JsonObject;
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
import java.util.Map;

import static util.PMUtil.PMMessage.TOKEN_INVALID;

@Controller
@RequestMapping("arena")
public class ArenaController {
    public HashMap<String, Arena> arenas = new HashMap<String, Arena>();
    public HashMap<String, Role> roles = new HashMap<String, Role>();

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
        PM pm = PMDao.get();
//        PM pm = new PM();
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
    @RequestMapping(value = "/walk", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map walk(@RequestParam(required = false) Integer steps, String token) {
        if (!validate(token)) {
            return this.jsonify(false, TOKEN_INVALID);
        }
        double ratio = 0.6;
        Role role = get_role(token);
        String ret = "没有遇到精灵";
        if (Math.random() < ratio) {
            PM pm = encounter(role);
            Arena arena = arena = new GrassArena(role, pm);
            System.out.println("arena role: " + role.getStagePM());
            System.out.println("arena pm : " + pm);
            arenas.put(token, arena);
            ret = role.toString() + "  encountered  \n" + pm.toString() + "\nstart fighting!";
            ret += "\n";
            JsonObject js_pm = JsonObject.mapFrom(pm);
            Map map = this.jsonify(true, "");
            map.put("pm", js_pm.toString());
            return map;
//            return ret;
        }
//        for(int i=0; i<5; i++){
//            role.add_pm(new PM());
//        }

        return this.kong();
    }

    private Map jsonify(boolean ret, String msg) {
        Map<String, String> map = new HashMap<String, String>();
        String sret = "fail";
        if (ret) {
            sret = "success";
        }
        map.put("code", sret);
        map.put("message", msg);
        return map;
    }

    private Map kong() {
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/attack", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map attack(String token, int skill_num) {
        if (!validate(token))
            return this.jsonify(false, TOKEN_INVALID);

        System.out.println("keys: " + arenas.keySet());
        if (arenas.containsKey(token)) {
            System.out.println("user exists");
            Arena arena = arenas.get(token);
            Map ret = arena.attack(1, skill_num - 1);
            if(!(boolean)ret.get("success")){
                return this.jsonify(false, "mp 不足");
            }
            Map map = this.jsonify(true, "");
            map.put("self", JsonObject.mapFrom(arena.stage_pm1).toString());
            map.put("opposite", JsonObject.mapFrom(arena.stage_pm2).toString());
            map.putAll(ret);
            return map;
        }
        return this.jsonify(true, "no arena exists");
    }


    @ResponseBody
    @RequestMapping(value = "/get_self", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Map get_self(@RequestParam String token) {
        if (!validate(token))
            return jsonify(false, TOKEN_INVALID);
        Arena arena = arenas.get(token);
        System.out.println("self: " + arena);
        Map map = this.jsonify(true, "");
        map.put("pm", JsonObject.mapFrom(arena.stage_pm1).toString());
        System.out.println(map.get("pm"));
        return map;
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
            Role role = roles.get(Login.TOKEN);
            if(role == null){
                role = new Role();
                roles.put(Login.TOKEN, role);
            }
            return role;
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/gtest", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object test() {
        int num = PMDao.get_length();
        return num;
    }
}
