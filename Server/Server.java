package Server;

import java.net.*;
import java.io.*;

public class Server extends Thread{
    private ServerSocket serverSocket;
    Server(int port) throws IOException{
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run(){
        while(true){
            try {
                System.out.println("Waiting for client on port "+serverSocket.getLocalPort()+"...");
                Socket socket = serverSocket.accept();
                System.out.println("A new connection recorded :" + socket.getRemoteSocketAddress());
                //
                DataInputStream dataIn = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                String in="";
                String out="";
                while(!out.equals("STOP")) {
                    out = dataIn.readUTF();
                    System.out.println(socket.getRemoteSocketAddress()+": "+out);

                    //
                    in = bufferedReader.readLine();
                    dataOut.writeUTF(in);
                    dataOut.flush();
                }
                socket.close();

            }catch (SocketTimeoutException e){
                System.out.println("Timed out");
                break;
            }catch ( IOException e){
                e.printStackTrace();
                break;
            }

        }
    }

    public static void main(String[] args) {
//        int port = Integer.parseInt(args[0]);
        try{
            Thread t = new Server(3334);
            t.start();
        }catch (IOException e){
            e.printStackTrace();
        }
//        ServerSocket ss=new ServerSocket(3333);
//        System.out.println("Server Socket created");
//        Socket s=ss.accept();
//        System.out.println("socket aceepted");
//        DataInputStream din=new DataInputStream(s.getInputStream());
//        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//
//        String str="",str2="";
//        while(!str.equals("stop")){
//            str=din.readUTF();
//            System.out.println("client says: "+str);
//            str2=br.readLine();
//            dout.writeUTF(str2);
//            dout.flush();
//        }
//        din.close();
//        s.close();
//        ss.close();

    }
}
