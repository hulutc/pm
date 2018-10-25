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

public class Skill {
    public int id;
    public String name;
    public PMProperty property;
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
            property = PROPLIST[rs.getInt("property")-1];
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
        return 3.3;
    }

    public Map attack(PM pm) {
        Map<String, Object> map = new HashMap<>();
        int hurt = 0;
        if (Arrays.asList(PM.PHYSICAL).contains(property)) {
            hurt = power - pm.fangyu;
            System.out.println(String.format("%d = %d - %d ", hurt, power, pm.fangyu));
            if (hurt < 0) {
                if (power > 0) {
                    hurt = 1;
                } else {
                    hurt = 0;
                }
            }
        } else {
            hurt = power - pm.tefang;
            System.out.println(String.format("%d = %d - %d ", hurt, power, pm.fangyu));
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
            die = true;
        }
        double ef = effect(pm);
        map.put("effect", ef);
        map.put("hurt", hurt);
        map.put("die", die);
        return map;
    }

    @Override
    public String toString() {
        return "No." + this.id + " " + this.name +  "  属性： " + PROPERTY_MAP_REVERSE.get(this.property) + "   威力:" + this.power + " 消耗mp：" + this.mp;
    }
}

