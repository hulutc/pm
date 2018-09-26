package Scene;

import PM.PM;
import Role.Role;
import Treasure.Treasure;

public class GrassArena extends Arena {

    public GrassArena(Role role1, Role role2) {
        super(role1, role2);
        this.name = "草地";
        this.property = Property.GRASS;
    }
}
