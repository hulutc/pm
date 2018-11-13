/*
 * tiancheng copyrights reserved
 */

import java.io.StringReader;
import java.util.*;

import PM.PM;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonParser;
import dao.UserDao;
import io.vertx.core.json.JsonObject;
import model.User;
import util.PMUtil;

public class hello {
    public String name;
    public int age;
    public static PM pm;
    public hello(){
        System.out.println("nihao");
        this.name = "tiancheng";
        this.age = 100;
    }
    
    public static void main(String[] args) throws InterruptedException {
        String b = "nihao";
        User user = UserDao.select("zhang");
        System.out.println(user);
        JSONObject jobj = JSON.parseObject("{\"name\":\"李明\",\"age\":19}");

        System.out.println("now: "+ new Date().getTime());
        Thread.sleep(1000);
        System.out.println("now: "+ new Date().getTime());

        System.out.println("nihao: "+ jobj.toString());
        System.out.printf("name:%s,age:%d\n",jobj.getString("name"),jobj.getBigInteger("age"));
        System.out.println(jobj.getInnerMap());

        JSONArray jarr = JSON.parseArray("[{\"name\":\"李明\",\"age\":19},{\"name\":\"张三\",\"age\":12}]");
        for(int i=0,len=jarr.size();i<len;i++){
            JSONObject temp=  jarr.getJSONObject(i);
            System.out.printf("name:%s,age:%d\n",temp.getString("name"),temp.getBigInteger("age"));
        }
    }
}


