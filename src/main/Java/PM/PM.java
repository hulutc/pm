package PM;

public class PM {
    String name;
    Property prop;

    String[] names = {"小火龙", "火恐龙", "喷火龙",
            "杰尼龟", "卡咪龟", "水箭龟",
            "妙蛙种子", "妙蛙草", "妙蛙花"};

    public PM(){
        int seed = (int)(Math.random()*names.length);
        init(names[seed]);
    }

    public PM(String name) {
        System.out.println("new pm");
        init(name);
    }

    public PM(String name, Property prop) {
        System.out.println("new pm");
        this.name = name;
        this.prop = prop;
    }

    public void init(String name){
        this.name = name;
        this.prop = new Property();
    }

    public String getName() {
        return this.name;
    }

    public int attack(PM pm) {
        return this.prop.attack(pm.prop);
    }

    public void show() {

    }

    public String toString()
    {
        return this.name + this.prop;
    }
//    public void attack(PM pm){
//        this.prop.
//    }


    public static void main(String[] args) {
        PM pm = new PM("小火龙");
        System.out.println(pm.toString());
        PM pm2 = new PM("妙蛙草");
        int c = pm2.attack(pm);
        System.out.println(c);
        Property p = new Property();
        p.gongji = 20;
        pm2.prop = p;
        System.out.println(pm2.attack(pm));
    }
};



