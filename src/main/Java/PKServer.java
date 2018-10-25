/*
 * tiancheng copyrights reserved
 */

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import PM.PM;
import Scene.GrassArena;
import Role.Role;
import Treasure.HaoShangYao;

public class PKServer extends Thread {
    private ServerSocket serverSocket;
    private Role role1;
    private Role role2;
    public int TIMEOUT = 100000;
    public int port = 3000;

    public PKServer() {
        init_arena();
    }

    public void init_arena() {
        role1 = new Role();
        List<PM> pms = new ArrayList<PM>();
        pms.add(new PM());
        role2 = new Role("hulu", Role.Sex.MALE, pms);
    }

    public void router() {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(TIMEOUT);
            while (true) {
                Socket server = serverSocket.accept();
                System.out.println(server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                String action = in.readUTF();
                String ret = flow(action);
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(ret);
                server.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String flow(String action) {
        GrassArena ga = createGrassArena(role1, role2);
        String ret = "";
        switch (action) {
            case "attack":
//                ret = ga.attack();
                break;
            case "alter":
                ret = ga.alter(role1, 2);
                break;
            case "run_away":
                ret = ga.run_way(role2);
                break;
            case "user_tool":
                ret = ga.use_tool(role1, new HaoShangYao());
                break;
            default:
                ret = "no such action";
                System.out.println(ret);
        }
        return ret;
    }

    public void run() {
        System.out.println("等待玩家接入...");
        router();
    }

//    public void run() {
//        System.out.println("等待玩家接入...");
//        while (true) {
//            try {
////                System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
//                Socket server = serverSocket.accept();
////                System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
//                DataInputStream in = new DataInputStream(server.getInputStream());
//                String action = in.readUTF();
//                System.out.println("get action: " + action);
//
//
//                GrassArena ga = createGrassArena(role1, role2);
//                Role role = new Role();
//                String ret = "";
//                switch (action) {
//                    case "attack":
//                        ret = ga.attack();
//                        break;
//                    case "alter":
//                        ret = ga.alter(role1, 2);
//                        break;
//                    case "run_away":
//                        ret = ga.run_way(role2);
//                        break;
//                    case "user_tool":
//                        ret = ga.use_tool(role1, new HaoShangYao());
//                        break;
//                    default:
//                        ret = "no such action";
//                        System.out.println(ret);
//                }
//                DataOutputStream out = new DataOutputStream(server.getOutputStream());
//                out.writeUTF(ret);
////                out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
//                server.close();
//            } catch (SocketTimeoutException s) {
//                System.out.println("Socket timed out!");
////                break;
//            } catch (IOException e) {
//                e.printStackTrace();
////                break;
//            }
//        }
//    }

    public GrassArena createGrassArena(Role r1, Role r2) {
        GrassArena ga = new GrassArena(r1, new PM());
//        GrassArena ga = new GrassArena(r1, r2);
        return ga;
    }

//    private void attack() {
//        System.out.println("attack");
//        int res = test1.attack(test2);
//        System.out.println("res: " + res);
//    }
//
//    private void run_away() {
//        System.out.println("player run away!");
//    }

    public static void main(String[] args) {
        Thread t = new PKServer();
        t.run();
    }
}