/*
 * tiancheng copyrights reserved
 */

package PM;

import util.PMUtil.PMProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static util.PMUtil.PROPERTY_MAP_REVERSE;
import static util.PMUtil.PROPERTY_ORDER;
import static util.PMUtil.PROPERTY_RESTRAINT;

public class Skill {
    public int id;
    public String name;
    public PMProperty property;
    public String property_cn;
    public int power;
    public int mp;
    public static PMProperty[] PROPLIST = {
            PMProperty.WATER, PMProperty.FIRE, PMProperty.ICE, PMProperty.GRASS,
            PMProperty.ELETRIC, PMProperty.PSYCHIC, PMProperty.EVIL, PMProperty.DRAGON,
            PMProperty.ROCK, PMProperty.GROUND, PMProperty.GHOST, PMProperty.FLYING,
            PMProperty.NORMAL, PMProperty.FIGHT, PMProperty.POISON, PMProperty.BUG,
            PMProperty.STEEL
    };

    public Skill(int id, String name) {
        init(id, name, 40, 2, PMProperty.NORMAL);
    }

    public Skill(int id, String name, int power, int mp, PMProperty property) {
        init(id, name, power, mp, property);
    }

    public Skill(ResultSet rs) {
        try {
            id = rs.getInt("id");
            name = rs.getString("name");
            property = PROPLIST[rs.getInt("property") - 1];
            property_cn = rs.getString("property_cn");
            power = rs.getInt("power");
            mp = rs.getInt("mp");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void init(int id, String name, int power, int mp, PMProperty property) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.mp = mp;
        this.property = property;
    }


    public double effect(PM pm) {
        int skill_int = PROPERTY_ORDER.get(property_cn);
        double effect = 1;
        for(String property: pm.properties){
            if(property!=null){
                effect *= PROPERTY_RESTRAINT[skill_int][PROPERTY_ORDER.get(property)];
                System.out.println("effect: " + effect);
            }
        }
        return effect;
    }

    private double computeDefensePercent(int fangyu) {
        int base = 400;
        int fenmu = 4000;
        double ret = ((double) (fangyu + base) / fenmu);
        double percent = Math.sin(ret * (Math.PI / 2));
        return percent;
    }

    public Map attack(PM pm) {
        Map<String, Object> map = new HashMap<>();
        int hurt = 0;
        if (Arrays.asList(PM.PHYSICAL).contains(property)) {
            hurt = (int)(power*effect(pm)*(1-this.computeDefensePercent(pm.fangyu)));
            System.out.println(String.format("物理 使用%s进行攻击, %d = %d - %d ", name, hurt, power, pm.fangyu));
            if (hurt < 0) {
                if (power > 0) {
                    hurt = 1;
                } else {
                    hurt = 0;
                }
            }
        } else {
            hurt = (int)(power*effect(pm)*(1-this.computeDefensePercent(pm.tefang)));
            System.out.println(String.format("特攻 使用%s进行攻击, %d = %d - %d ", name, hurt, power, pm.fangyu));
            if (hurt < 0) {
                if (power > 0) {
                    hurt = 1;
                } else {
                    hurt = 0;
                }
            }
        }

        pm.hp -= hurt;
        boolean die = false;
        if (pm.hp <= 0) {
            pm.hp = 0;
            die = true;
        }
        double ef = effect(pm);
        map.put("success", true);
        map.put("effect", ef);
        map.put("hurt", hurt);
        map.put("die", die);
        return map;
    }

    @Override
    public String toString() {
        return "No." + this.id + " " + this.name + "  属性： " + PROPERTY_MAP_REVERSE.get(this.property) + "   威力:" + this.power + " 消耗mp：" + this.mp;
    }
}

