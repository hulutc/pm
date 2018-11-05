/*
 * tiancheng copyrights reserved
 */

package util;

import java.util.HashMap;
import java.util.Map;

public class PMUtil {
    public enum PMProperty {
        WATER, FIRE, ICE, GRASS,
        ELETRIC, PSYCHIC, EVIL, DRAGON,
        ROCK, GROUND, GHOST, FLYING,
        NORMAL, FIGHT, POISON, BUG,
        STEEL
    }

    public static Map<String, PMProperty> PROPERTY_MAP;

    static {
        PROPERTY_MAP = new HashMap<String, PMProperty>();
        PROPERTY_MAP.put("水", PMProperty.WATER);
        PROPERTY_MAP.put("火", PMProperty.FIRE);
        PROPERTY_MAP.put("冰", PMProperty.ICE);
        PROPERTY_MAP.put("草", PMProperty.GRASS);
        PROPERTY_MAP.put("电", PMProperty.ELETRIC);
        PROPERTY_MAP.put("超能", PMProperty.PSYCHIC);
        PROPERTY_MAP.put("恶", PMProperty.EVIL);
        PROPERTY_MAP.put("龙", PMProperty.DRAGON);
        PROPERTY_MAP.put("岩石", PMProperty.ROCK);
        PROPERTY_MAP.put("地面", PMProperty.GROUND);
        PROPERTY_MAP.put("幽灵", PMProperty.GHOST);
        PROPERTY_MAP.put("飞行", PMProperty.FLYING);
        PROPERTY_MAP.put("普通", PMProperty.NORMAL);
        PROPERTY_MAP.put("格斗", PMProperty.FIGHT);
        PROPERTY_MAP.put("毒", PMProperty.POISON);
        PROPERTY_MAP.put("虫", PMProperty.BUG);
        PROPERTY_MAP.put("钢", PMProperty.STEEL);
    }

    public static Map<PMProperty, String> PROPERTY_MAP_REVERSE;

    static {
        PROPERTY_MAP_REVERSE = new HashMap<PMProperty, String>();
        PROPERTY_MAP_REVERSE.put(PMProperty.WATER, "水");
        PROPERTY_MAP_REVERSE.put(PMProperty.FIRE, "火");
        PROPERTY_MAP_REVERSE.put(PMProperty.ICE, "冰");
        PROPERTY_MAP_REVERSE.put(PMProperty.GRASS, "草");
        PROPERTY_MAP_REVERSE.put(PMProperty.ELETRIC, "电");
        PROPERTY_MAP_REVERSE.put(PMProperty.PSYCHIC, "超能");
        PROPERTY_MAP_REVERSE.put(PMProperty.EVIL, "恶");
        PROPERTY_MAP_REVERSE.put(PMProperty.DRAGON, "龙");
        PROPERTY_MAP_REVERSE.put(PMProperty.ROCK, "岩石");
        PROPERTY_MAP_REVERSE.put(PMProperty.GROUND, "地面");
        PROPERTY_MAP_REVERSE.put(PMProperty.GHOST, "幽灵");
        PROPERTY_MAP_REVERSE.put(PMProperty.FLYING, "飞行");
        PROPERTY_MAP_REVERSE.put(PMProperty.NORMAL, "普通");
        PROPERTY_MAP_REVERSE.put(PMProperty.FIGHT, "格斗");
        PROPERTY_MAP_REVERSE.put(PMProperty.POISON, "毒");
        PROPERTY_MAP_REVERSE.put(PMProperty.BUG, "虫");
        PROPERTY_MAP_REVERSE.put(PMProperty.STEEL, "钢");
    }

    //17
    public static Map<String, Integer> PROPERTY_ORDER;
    static {
        PROPERTY_ORDER = new HashMap<String, Integer>();
        PROPERTY_ORDER.put("普通", 0);
        PROPERTY_ORDER.put("火", 1);
        PROPERTY_ORDER.put("水", 2);
        PROPERTY_ORDER.put("草", 3);
        PROPERTY_ORDER.put("电", 4);
        PROPERTY_ORDER.put("冰", 5);
        PROPERTY_ORDER.put("格斗", 6);
        PROPERTY_ORDER.put("毒", 7);
        PROPERTY_ORDER.put("地面", 8);
        PROPERTY_ORDER.put("飞行", 9);
        PROPERTY_ORDER.put("超能", 10);
        PROPERTY_ORDER.put("虫", 11);
        PROPERTY_ORDER.put("岩石", 12);
        PROPERTY_ORDER.put("幽灵", 13);
        PROPERTY_ORDER.put("龙", 14);
        PROPERTY_ORDER.put("恶", 15);
        PROPERTY_ORDER.put("钢", 16);
    }

    public static double H2 = 2;
    public static double H1 = 1;
    public static double H0 = 0.5;
    public static double NO = 0;

    public static double[][] PROPERTY_RESTRAINT = {
            {H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H0, NO, H1, H1, H0, H1},
            {H1, H0, H0, H2, H1, H2, H1, H1, H1, H1, H1, H2, H0, H1, H0, H1, H2, H1},
            {H1, H2, H0, H0, H1, H1, H1, H1, H2, H1, H1, H1, H2, H1, H0, H1, H1, H1},
            {H1, H0, H2, H0, H1, H1, H1, H0, H2, H0, H1, H0, H2, H1, H0, H1, H0, H1},
            {H1, H1, H2, H0, H0, H1, H1, H1, NO, H2, H1, H1, H1, H1, H0, H1, H1, H1},
            {H1, H0, H0, H2, H1, H0, H1, H1, H2, H2, H1, H1, H1, H1, H2, H1, H0, H1},
            {H2, H1, H1, H1, H1, H2, H1, H0, H1, H0, H0, H0, H2, NO, H1, H2, H2, H0},
            {H1, H1, H1, H2, H1, H1, H1, H0, H0, H1, H1, H1, H0, H0, H1, H1, NO, H2},
            {H1, H2, H1, H0, H2, H1, H1, H2, H1, NO, H1, H0, H2, H1, H1, H1, H2, H1},
            {H1, H1, H1, H2, H0, H1, H2, H1, H1, H1, H1, H2, H0, H1, H1, H1, H0, H1},
            {H1, H1, H1, H1, H1, H1, H2, H2, H1, H1, H0, H1, H1, H1, H1, NO, H0, H1},
            {H1, H0, H1, H2, H1, H1, H0, H0, H1, H0, H2, H1, H1, H0, H1, H2, H0, H0},
            {H1, H2, H1, H1, H1, H2, H0, H1, H0, H2, H1, H2, H1, H1, H1, H1, H0, H1},
            {NO, H1, H1, H1, H1, H1, H1, H1, H1, H1, H2, H1, H1, H2, H1, H0, H1, H1},
            {H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H1, H2, H1, H0, NO},
            {H1, H1, H1, H1, H1, H1, H0, H1, H1, H1, H2, H1, H1, H2, H1, H0, H1, H0},
            {H1, H0, H0, H1, H0, H2, H1, H1, H1, H1, H1, H1, H2, H1, H1, H1, H0, H2},
    };

    public class PMMessage {
        public static final String TOKEN_INVALID = "token invalid";
    }
}
