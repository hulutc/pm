/*
 * tiancheng copyrights reserved
 */

package controller;

import PM.PM;
import Role.Role;
import Scene.Arena;
import Scene.GrassArena;
import dao.PMDao;
import dao.UserDao;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import login.Login;
import model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static util.PMUtil.PMMessage.TOKEN_INVALID;
import static util.Util.StatusFail;
import static util.Util.jsonify;

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
    public ResponseEntity walk(@RequestParam(required = false) Integer steps, String token) {
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
            ResponseEntity re = jsonify(true, "");
            Map map = (Map) re.getBody();
            map.put("pm", js_pm.toString());
            return re;
//            return ret;
        }
//        for(int i=0; i<5; i++){
//            role.add_pm(new PM());
//        }

        HashMap<String, Object> data = new HashMap<String, Object>(){{
            put("pm", null);
        }};
        System.out.println("leave walk");
        return jsonify(false, "未遇到精灵", data);
    }


    private Map kong() {
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/attack", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity attack(String token, int skill_num) {

        System.out.println("keys: " + arenas.keySet());
        if (arenas.containsKey(token)) {
            System.out.println("user exists");
            Arena arena = arenas.get(token);
            Map ret = arena.attack(1, skill_num - 1);
            if(!(boolean)ret.get("success")){
                return jsonify(false, "mp 不足");
            }
            ResponseEntity re = jsonify(true, "");
            Map map = (Map) re.getBody();
            map.put("self", JsonObject.mapFrom(arena.stage_pm1).toString());
            map.put("opposite", JsonObject.mapFrom(arena.stage_pm2).toString());
            map.putAll(ret);
            return re;
        }
        return jsonify(true, "no arena exists");
    }


    @ResponseBody
    @RequestMapping(value = "/get_self", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public ResponseEntity get_self(@RequestParam String token) {
//        if (!validate(token))
//            return jsonify(false, TOKEN_INVALID);
        Arena arena = arenas.get(token);
        System.out.println("self: " + arena);
        ResponseEntity re = jsonify(true, "");
        Map map = (Map) re.getBody();
        map.put("pm", JsonObject.mapFrom(arena.stage_pm1).toString());
        System.out.println(map.get("pm"));
        return re;
    }


    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map login(@RequestParam String username, String password, HttpServletResponse response) {
        Map map = Login.login(username, password);
        if(map.get("code").equals(StatusFail)){
            response.setStatus(400);
        }else{
            response.addCookie(new Cookie("token", (String)map.remove("token")));
        }
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
    public HttpServletResponse test(HttpServletResponse response) {

        return response;
//        return jsonify(true, "nihao");
    }

    public static void usertest(){
        String b = "nihao";
        User user = UserDao.select("zhang");
        System.out.println(user);
    }

    @RequestMapping("/addCookie")
    public ResponseEntity addCookie(HttpServletResponse response, String name){
        Cookie cookie = new Cookie("jwt", "tiancheng");
        cookie.setMaxAge(30 * 60);// 设置为30min
        cookie.setPath("/");
        System.out.println("已添加===============");
        response.addCookie(cookie);
        return jsonify(true, "sucess");
    }

    @RequestMapping("/getCookie")
    public ResponseEntity getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        for(Cookie ck: cookies){
            System.out.println(ck.getName() + ": " + ck.getValue());
        }
        return jsonify(true, "sucess");
    }


}
