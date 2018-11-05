/*
 * tiancheng copyrights reserved
 */

import java.util.*;

import PM.PM;
import io.vertx.core.json.JsonObject;
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
    
    public static void main(String[] args) {
        String b = "nihao";
        String[] c = b.split(",");
//        String[] c = a.split(",");

        JsonObject jb = JsonObject.mapFrom(new hello());
        Map<String, String> map = new HashMap<>();
        if(map.get("nihao") == null){
            System.out.println("nihao");
        }
        System.out.println();
        int bb = 50;
        int base = 400;
        int fenmu = 4000;
        double ret = ((double)(bb+base)/fenmu);
        double a = Math.sin(ret*(Math.PI/2));
        System.out.println(a);

        String[] strs = new String[2];
        for(String str: strs){
            System.out.println(str);
        }
        //        Random random  = new Random([1,2,3]);
    }
}


