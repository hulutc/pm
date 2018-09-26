package Treasure;

import Role.Role;

public class Tool extends Treasure {
    public int id;
    public String name;
    public float price;

    @Override
    public void use(Role user) {
        System.out.println(user + " 使用了 " + this);
    }

    public String toString() {
        return name;
    }
}
