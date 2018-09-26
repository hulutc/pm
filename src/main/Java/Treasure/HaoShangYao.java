package Treasure;

import Role.Role;

public class HaoShangYao extends Treasure {

    public HaoShangYao(){
        name = "好伤药";
    }

    @Override
    public void use(Role user) {
        System.out.println(user + " 使用了 " + this);
    }
}
