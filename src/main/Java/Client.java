/*
 * tiancheng copyrights reserved
 */

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Client {
    public void post(Socket client, String data) {
        String action = "";
        try {
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String get_reply(Socket client) {
        String ret = "";
        try {
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            ret = in.readUTF();
            client.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public void run() {
        String serverName = "localhost";
        int port = 3000;
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("action：");

            while (scan.hasNextLine()) {
                String action = scan.nextLine();
                Socket client = new Socket(serverName, port);
                post(client, action);
                String ret = get_reply(client);
                System.out.println(ret);
            }
            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
        cli.run();
    }
}