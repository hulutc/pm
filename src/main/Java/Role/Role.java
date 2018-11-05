package Role;

import PM.PM;
import dao.PMDao;

import java.util.ArrayList;
import java.util.List;

public class Role {
    public String name = "tiancheng";
    public enum Sex {
        MALE, FEMALE
    }

    public Sex sex = Sex.MALE;
    public List<PM> pms = new ArrayList<PM>();
    public int MAX_PM_NUM = 6;

    public Role() {
        this.name = "tiancheng";
        this.sex = Sex.MALE;
        pms.add(PMDao.get());
    }


    public Role(String name, Sex sex, List<PM> pms) {
        this.name = name;
        this.sex = sex;
        this.pms.addAll(pms);
    }

    public void add_pm(PM pm){
        this.pms.add(pm);
    }

    /*
    精灵死亡后，更换精灵
     */
    public PM change_pm(){
        PM pm = null;
        for(int i=0; i<pms.size(); i++){
            if(pms.get(i).hp > 0){
                pm = pms.get(i);
                pms.remove(i);
                break;
            }
        }
        if(pm != null) {
            pms.add(0, pm);
        }
        return pm;
    }

    public void alter_pm(int new_index){
        System.out.println("alter pm");
        PM old = pms.get(0);
        PM _new = pms.get(new_index);
        pms.remove(0);
        pms.add(0, pms.get(new_index));
        pms.remove(_new);
        pms.add(new_index, old);
    }


    public void catch_pm(PM pm){
        this.pms.add(pm);
    }

    public PM getStagePM(){
        return pms.get(0);
    }

    public void show_pms(){
        System.out.println(this.name + ": " + this.pms);
    }

    public String toString(){
        return this.name + "(" + pms.size() + "个精灵)";
    }


}
