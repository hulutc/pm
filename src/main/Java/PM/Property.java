package PM;

public class Property {
    int hp = 15;
    int mp = 5;
    int gongji = 10;
    int fangyu = 10;
    int tegong = 10;
    int tefang = 10;
    int minjie = 10;

    public int attack(Property prop) {
        int a = this.gongji - prop.gongji;
        return a;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public int getGongji() {
        return gongji;
    }

    public int getFangyu() {
        return fangyu;
    }

    public int getTegong() {
        return tegong;
    }

    public int getTefang() {
        return tefang;
    }

    public int getMinjie() {
        return minjie;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setGongji(int gongji) {
        this.gongji = gongji;
    }

    public void setFangyu(int fangyu) {
        this.fangyu = fangyu;
    }

    public void setTegong(int tegong) {
        this.tegong = tegong;
    }

    public void setTefang(int tefang) {
        this.tefang = tefang;
    }

    public void setMinjie(int minjie) {
        this.minjie = minjie;
    }

    public String toString() {
//            return
        return ""+(this.gongji+this.fangyu);
    }
}
