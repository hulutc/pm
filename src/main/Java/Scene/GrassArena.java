/*
 * tiancheng copyrights reserved
 */

package Scene;

import PM.PM;
import Role.Role;

public class GrassArena extends Arena {


    public GrassArena(Role role, PM pm) {
        super(role, pm);
        this.name = "草地";
        this.property = Property.GRASS;
    }
}
