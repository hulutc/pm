/*
 * tiancheng copyrights reserved
 */

package dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonParser;
import io.vertx.core.json.JsonObject;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginDao {
    public static String KEY = "hlpokemon:token:token_user";
    public static String KEY_USER_TOKEN = "hlpokemon:token:user_token";

    public static String setToken(String username){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        Jedis jedis = Redis.getJedis();

        String token = jedis.hget(KEY_USER_TOKEN, username);
        if(token!= null && !token.isEmpty()){
            jedis.hdel(KEY, token);
            System.out.println("hdel " + token);
        }
        JSONObject map = new JSONObject();
//        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("last_request_time", new Date().getTime());
//        map.put("expire", new Date().getTime());
        jedis.hset(KEY, uuid.toString(), map.toString());
        jedis.hset(KEY_USER_TOKEN, username, uuid.toString());
        return uuid.toString();
    }

    public static Map getUserInfo(String token){
        Jedis jedis = Redis.getJedis();
        String ret = jedis.hget(KEY, token);
        System.out.println(ret);
        JSONObject jo = JSON.parseObject(ret);
        Map inner = jo.getInnerMap();
        System.out.println(inner);
        return inner;
    }
}
