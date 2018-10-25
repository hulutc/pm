/*
 * tiancheng copyrights reserved
 */

package util;

import java.util.Dictionary;
import java.util.Enumeration;
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
    static{
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
    static{
        PROPERTY_MAP_REVERSE = new HashMap<PMProperty, String>();
        PROPERTY_MAP_REVERSE.put(PMProperty.WATER, "水");
        PROPERTY_MAP_REVERSE.put( PMProperty.FIRE,"火");
        PROPERTY_MAP_REVERSE.put( PMProperty.ICE,"冰");
        PROPERTY_MAP_REVERSE.put( PMProperty.GRASS,"草");
        PROPERTY_MAP_REVERSE.put( PMProperty.ELETRIC,"电");
        PROPERTY_MAP_REVERSE.put( PMProperty.PSYCHIC,"超能");
        PROPERTY_MAP_REVERSE.put( PMProperty.EVIL,"恶");
        PROPERTY_MAP_REVERSE.put( PMProperty.DRAGON,"龙");
        PROPERTY_MAP_REVERSE.put( PMProperty.ROCK,"岩石");
        PROPERTY_MAP_REVERSE.put( PMProperty.GROUND,"地面");
        PROPERTY_MAP_REVERSE.put( PMProperty.GHOST,"幽灵");
        PROPERTY_MAP_REVERSE.put( PMProperty.FLYING,"飞行");
        PROPERTY_MAP_REVERSE.put( PMProperty.NORMAL,"普通");
        PROPERTY_MAP_REVERSE.put( PMProperty.FIGHT,"格斗");
        PROPERTY_MAP_REVERSE.put( PMProperty.POISON,"毒");
        PROPERTY_MAP_REVERSE.put( PMProperty.BUG,"虫");
        PROPERTY_MAP_REVERSE.put( PMProperty.STEEL,"钢");
    }


    public class PMMessage {
        public static final String TOKEN_INVALID = "token invalid";
    }
}
