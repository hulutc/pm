/*
 * tiancheng copyrights reserved
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import PM.PM;
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
        String a = "ni,hao";
        String b = "nihao";
        String[] c = b.split(",");
//        String[] c = a.split(",");

        System.out.println(c.length+" " + c[0]);
//        Random random  = new Random([1,2,3]);
    }
}


