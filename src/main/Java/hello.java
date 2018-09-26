import java.util.ArrayList;
import java.util.List;

public class hello {
    public String name;
    public int age;
    public hello(){
        System.out.println("nihao");
        this.name = "tiancheng";
        this.age = 100;
    }
    
    public static void main(String[] args) {
        hello hl = new hello();
        List list = new ArrayList();
        list.add(1);
        System.out.println(list.size());

        System.out.println(hl.name);
    }
}


