package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",3334);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
            DataInputStream din=new DataInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);
            String userInput;
            String response;
            String clientName = "";
            ClientThread clientThread = new ClientThread(socket);
            clientThread.start();
            do {
                if (clientName.equals("")){
                    System.out.println("Enter your Name");
                    userInput=scanner.nextLine();
                    clientName=userInput;
                    output.println(userInput);
                    if (userInput.equals("EXIT")){
                        break;
                    }

                }else{
                    String messgae= ("("+clientName+")"+" message : ");
                    System.out.println(messgae);
//                    System.out.println("//"+din.readUTF());
                    userInput =  scanner.nextLine();
                    output.println(messgae+" "+userInput);
                    if(userInput.equals("EXIT")){
                        break;
                    }



                }

            }while (!userInput.equals("EXIT"));


        }catch (Exception e){
            System.out.println("Error occoured in client");
            e.printStackTrace();

        }
    }
}
