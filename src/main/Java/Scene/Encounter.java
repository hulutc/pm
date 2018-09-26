package Scene;

import java.util.LinkedList;
import java.util.List;
import PM.PM;

public class Encounter implements Runnable{
    public static boolean LOCK = true;
    List<PM> pms = new LinkedList<PM>();
    public void run(){
        while(true){
            synchronized (this){
                try{
                    double prob = Math.random()*100 + 1;
                    if (prob > 50 && prob < 70) {
                        PM pm = new PM();
                        pms.add(pm);
                        System.out.println("encounter: " + pm.getName());
                    }
                    Thread.sleep(600);
                }catch (InterruptedException e){
                    System.out.println(e.getStackTrace().toString());
                }
            }
        }

    }

    public void start(){
        Thread t = new Thread(this, "thread1");
        t.start();
    }


    public List get_pms(){
        synchronized (this){
            return this.pms;
        }
    }

    public void show_pms(){
        synchronized (this){
            System.out.println(this.pms);
        }
    }
    public static void main(String[] args) {
        Encounter et = new Encounter();
        et.start();
        while(true) {
            try{
                Thread.sleep(3000);
                et.show_pms();
            }catch (InterruptedException e){
                System.out.println(e);
            }catch (Exception e ){
                System.out.println(e);
            }
        }

    }

}
