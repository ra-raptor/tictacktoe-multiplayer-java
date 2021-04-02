package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        System.out.println("Server Started");
        try{
            ServerSocket serverSocket = new ServerSocket(3334);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("new connection");
                ServerThread serverThread = new ServerThread(socket,threadList);
                threadList.add(serverThread);
                serverThread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
