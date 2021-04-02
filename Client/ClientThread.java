package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private BufferedReader input;
    private DataInputStream din;

    ClientThread(Socket s) throws IOException{
        this.socket = s;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.din=new DataInputStream(s.getInputStream());
    }

    @Override
    public void run() {
        try{
            while (true){
                String response = input.readLine();
                System.out.println("res");
                System.out.println(response);
//                System.out.println("Custom outout : "+din.readUTF());

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                input.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
