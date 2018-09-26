package Treasure;

import Role.Role;

public abstract class Treasure {
    public int id;
    public String name;
    public float price;


    abstract public void use(Role user);

    public String toString(){
        return this.name;
    }
}
