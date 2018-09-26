package Scene;

import PM.PM;
import Role.Role;
import Treasure.Treasure;


public class Arena {
    public static enum Property {
        WATER, FIRE, SNOW, GRASS,
        ELECTRICITY, Mental, EVIL, DRAGON
//        FIGHT,
    }

    public String name;
    public Property property;
    public Role role1;
    public Role role2;
    public PM stage_pm1;
    public PM stage_pm2;


    public Arena(Role role1, Role role2) {
        this.role1 = role1;
        this.role2 = role2;
        this.stage_pm1 = role1.getStagePM();
        this.stage_pm2 = role2.getStagePM();
    }

    public void flow() {
        /*
        ask do
         */

    }

    public String attack() {
        int hp = stage_pm1.attack(stage_pm2);
        String ret = stage_pm1.toString() + " PK " + stage_pm2.toString() + " :  " + hp;
        System.out.println(ret);
        return ret;
    }

    public String alter(Role role, int new_index) {
        role.alter_pm(new_index);
        String ret = role.pms.get(new_index) + " 换下 " + role.getStagePM();
        System.out.println(ret);
        return ret;
    }

    public String run_way(Role role) {
        String ret = role + " run_away!";
        System.out.println(ret);
        return ret;
    }

    public String use_tool(Role role, Treasure treasure) {
        String ret = role + "给 " + role.getStagePM() + " 使用了 " + treasure;
        System.out.println(ret);
        return ret;
    }

}

