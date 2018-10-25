/*
 * tiancheng copyrights reserved
 */

package PM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Dao;
import dao.PMDao;
import dao.SkillDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import util.PMUtil.PMProperty;
import static util.PMUtil.PROPERTY_MAP;
import javax.sql.DataSource;



public class PM {
    /*
    宝石版：
    特攻：水火冰草电超恶龙
    物攻：岩地鬼飞普格毒虫钢
     */


    public static PMProperty[] PHYSICAL = {
            PMProperty.ROCK, PMProperty.GROUND, PMProperty.GHOST, PMProperty.FLYING,
            PMProperty.NORMAL, PMProperty.FIGHT, PMProperty.POISON, PMProperty.BUG,
            PMProperty.STEEL
    };
    public static PMProperty[] MENTAL = {
            PMProperty.FIRE, PMProperty.WATER, PMProperty.ICE, PMProperty.GRASS,
            PMProperty.ELETRIC, PMProperty.PSYCHIC, PMProperty.EVIL, PMProperty.DRAGON,
    };



    public int pm_id;
    public int pokemon_id;
    public String name;
    public PMProperty[] properties = {PMProperty.NORMAL, null};
    public ArrayList<Skill> skills = new ArrayList<>();

    public int hp = 0;
    public int mp = 0;
    public int gongji = 0;
    public int fangyu = 0;
    public int tegong = 0;
    public int tefang = 0;
    public int minjie = 0;
    public int die_exp = 0;  //被击败后，对方能够获取的经验值


    private int exp = 0;     //获取到的经验值，升级后清零
    public int level = 0;
    public int level_max = 30;

    String[] names = {"小火龙", "火恐龙", "喷火龙",
            "杰尼龟", "卡咪龟", "水箭龟",
            "妙蛙种子", "妙蛙草", "妙蛙花"};

    public PM(){
        int seed = (int)(Math.random()*names.length);
        init(names[seed]);
        Skill skill = new Skill(1,"飞叶快刀");
        skills.add(skill);
        skills.add(new Skill(9,"催眠粉", 0, 1, PMProperty.GRASS));
    }


    public PM(String name) {
        System.out.println("new pm");
        init(name);
    }

    public PM(ResultSet resultSet){
        try {
            pm_id = resultSet.getInt("id");
            name = resultSet.getString("name");
            String prop = resultSet.getString("property");
            String[] pps = prop.split(",");
            properties[0] = PROPERTY_MAP.get(pps[0]);
            if (pps.length  > 1) {
                properties[1] = PROPERTY_MAP.get(pps[1]);

            }
            skills = new ArrayList<>();
            String[] sks = resultSet.getString("skills").split(",");
            if(sks.length==1){
                skills.add(SkillDao.select(Integer.parseInt(sks[0])));
            }else{
                for(String sk: sks){
                    skills.add(SkillDao.select(Integer.parseInt(sk)));
                }
            }
            levelup(resultSet);
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void load(ResultSet resultSet){
        // 加载pokemon时用到的方法
        try {
            pm_id = resultSet.getInt("id");
            name = resultSet.getString("name");
            String prop = resultSet.getString("property");
            String[] pps = prop.split(",");
            properties[0] = PROPERTY_MAP.get(pps[0]);
            if (pps.length  > 1) {
                properties[1] = PROPERTY_MAP.get(pps[1]);

            }
            hp = resultSet.getInt("hp");
            mp = resultSet.getInt("mp");
            gongji = resultSet.getInt("gongji");
            fangyu = resultSet.getInt("fangyu");
            tegong = resultSet.getInt("tegong");
            tefang = resultSet.getInt("tefang");
            minjie = resultSet.getInt("minjie");
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void init(String name){
        this.name = name;
    }

    public void levelup(ResultSet rs){
        set_levelup(rs);
    }

    // TODO
//    public void levelup(){
//        Dao dao = new Dao();
//        ResultSet rs = PMDao.select(pm_id);
//        set_levelup(rs);
//    }

    private void set_levelup(ResultSet rs){
        try {
            hp += get_growth(rs.getInt("hp_min"), rs.getInt("hp_max"));
            mp += get_growth(rs.getInt("mp_min"), rs.getInt("mp_max"));
            gongji += get_growth(rs.getInt("gongji_min"), rs.getInt("gongji_max"));
            fangyu += get_growth(rs.getInt("fangyu_min"), rs.getInt("fangyu_max"));
            tegong += get_growth(rs.getInt("tegong_min"), rs.getInt("tegong_max"));
            tefang += get_growth(rs.getInt("tefang_min"), rs.getInt("tefang_max"));
            minjie += get_growth(rs.getInt("minjie_min"), rs.getInt("minjie_max"));
            die_exp += get_growth(rs.getInt("die_exp_min"), rs.getInt("die_exp_max"));
            this.level += 1;
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    private int get_growth(int min, int max){
        int ret = (int)(Math.random() * (max - min));
        return ret + min;
    }

    public String getName() {
        return this.name;
    }

    public Map attack(int skill_num, PM pm) {
        Skill skill = pm.skills.get(skill_num);
        return skill.attack(pm);
    }

    public void show() {

    }

    public String toString()
    {
        String str = name + "\n";
        str += "生命:" + this.hp;
        str += "  魔法:" + this.mp;
        str += "  攻击:" + this.gongji;
        str += "  防御:" + this.fangyu;
        str += "  特攻:" + this.tegong;
        str += "  特防:" + this.tefang;
        for(Skill skill: skills){
            str += "\n" + skill;
        }
        return str;
    }
//    public void attack(PM pm){
//        this.prop.
//    }


    public static void main(String[] args) {
    }
};



