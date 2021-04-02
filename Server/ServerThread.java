package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> threadArrayList;
//    private PrintWriter output;
    private DataOutputStream dataOut;

    ServerThread(Socket socket,ArrayList<ServerThread> threads){
        this.socket = socket;
        this.threadArrayList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOut = new DataOutputStream(socket.getOutputStream());
//            output = new PrintWriter(socket.getOutputStream(),true);

            while (true){
                String outputString = bufferedReader.readLine();
                if(outputString.equals("EXIT")){
                        break;
                }
                printToAllClients(outputString);
                System.out.println("Server recieved "+outputString);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void printToAllClients(String outputString){
        for(ServerThread s:threadArrayList){
//            s.output.println(outputString);
            try {
                System.out.println("writing"+outputString);
                s.dataOut.writeUTF(outputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
