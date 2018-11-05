/*
 * tiancheng copyrights reserved
 */

package Scene;

import PM.PM;
import Role.Role;
import Treasure.Treasure;

import java.util.Map;


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

    public Arena(Role role, PM pm){
        this.role1 = role;
        this.stage_pm1 = role.getStagePM();
        this.stage_pm2 = pm;
    }

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

    /**
     *
     * @param atk_order  1:stage_pm1攻击   2: stage_pm2攻击
     * @return
     */
    public Map attack(int atk_order, int skill_num) {
        PM pma, pmb;
        Role rolea, roleb;
        if(atk_order == 1){
            pma = this.stage_pm1;
            rolea = this.role1;
            pmb = this.stage_pm2;
            roleb = this.role2;
        }else{
            pma = this.stage_pm2;
            rolea = this.role2;
            pmb = this.stage_pm1;
            roleb = this.role1;
        }
        Map map= pma.attack(skill_num, pmb);
        if(!(boolean)map.get("success")){
            return map;
        }
        int hurt = (int) map.get("hurt");
        double effect = (double) map.get("effect");
        boolean die = (boolean) map.get("die");
        System.out.println("hurt: "+hurt);
        String ret = String.format("%s 对 %s 使用了 %s", pma.name, pmb.name, pma.skills.get(skill_num).name);
        if(effect < 2){
            ret += "\n效果普通";
        }else{
            ret += "\n效果拔群";
        }
        ret += String.format("\n%s 受到了 %d 点伤害", pmb.name, hurt);
        if(die){
            //TODO 逻辑应该是要重写的
//            ret += String.format("\n%s 被击败， %s 获得 %d 经验值!", pmb.name, pma.name, pmb.die_exp);
//            boolean levelup = pma.setExp(pmb.die_exp);
//            if(levelup){
//                ret += String.format("\n恭喜！%s 升到了 %d 级", pma.name, pma.level);
//                ret += "\n" + pma;
//            }

            if(atk_order == 1 && roleb == null){
                //TODO
            }else {
                PM changed_pm = roleb.change_pm();
                if (changed_pm == null) {
                    ret += String.format("\n%s 无健康精灵可上场，败北而归!", roleb.name);
                }else{
                    ret += String.format("\n%s 换上了 %s 继续战斗", roleb.name, changed_pm.name);
                }
            }
            ret += "\n战斗结束";
        }else{
            ret += "\n\n\n" + pmb;
            ret += "\n\n" + pma;
        }

        return map;
//        int hp = stage_pm1.attack(stage_pm2);
//        String ret = stage_pm1.toString() + " PK " + stage_pm2.toString() + " :  " + hp;
//        System.out.println(ret);
//        return ret;
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

    @Override
    public String toString() {
        String titles = this.name;
        if(role2 == null){
            titles += "\n" + "野生的 " + stage_pm2 + " 跳出来了.";
        }
        if(role1 != null){
            stage_pm1 = role1.getStagePM();
            titles += "\n" + role1 + " 使用 " + stage_pm1 + " 进行战斗！";
        }
        if(role2 != null){
            titles += "\n" + role2 + " 使用 " + stage_pm2 + " 进行战斗！";
        }

        return titles;
    }
}

