package Treasure;

import PM.PM;
import Role.Role;

public class PokeBall extends Tool {

    public PokeBall() {
        this.id = 0x0001;
        this.name = "精灵球";
    }

    public PokeBall(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public void use(Role user) {
        PM pm = new PM();
        user.add_pm(pm);
    }
}
