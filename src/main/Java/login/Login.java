/*
 * tiancheng copyrights reserved
 */

package login;

import dao.LoginDao;
import dao.Redis;
import dao.UserDao;
import model.User;
import org.springframework.http.ResponseEntity;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static util.Util.StatusFail;
import static util.Util.StatusSuccess;
import static util.Util.jsonify;

public class Login {
    public static final String TOKEN = "tianchenghulu";
    public static final double EXPIRE = 20*1000;

    public static Map login(String username, String password) {
        User user = UserDao.select(username);
        Map<String, String> map = new HashMap<String, String>();
        if (user == null) {
            map.put("code", StatusFail);
            map.put("message", "用户名不存在");
            return map;
        } else {
            if (password.equals(user.password)) {
                map.put("code", StatusSuccess);
                map.put("message", "登录成功");
                map.put("token", LoginDao.setToken(username));
                return map;
            } else {
                map.put("code", StatusFail);
                map.put("message", "密码错误");
                return map;
            }
        }

    }

    private static boolean Verify(String token){
        Map userinfo = LoginDao.getUserInfo(token);
        double expire = (double) userinfo.get("expire");

        return false;
    }


}
