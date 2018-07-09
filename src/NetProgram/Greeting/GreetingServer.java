package NetProgram.Greeting;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class GreetingServer extends Thread {
    private ServerSocket serverSocket;
    public GreetingServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
//        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        try {
            System.out.println("Server started at " + new Date());
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
            while (true) {
                //Listen for connection
                Socket server = serverSocket.accept();
                System.out.println("Just connected from " + server.getRemoteSocketAddress());
                //create input and output
                DataOutputStream outToClient = new DataOutputStream(server.getOutputStream());
                DataInputStream inFromClient = new DataInputStream(server.getInputStream());


                outToClient.writeUTF("connection established to " + server.getLocalSocketAddress());
                server.close();
            }

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        try {
            Thread t = new GreetingServer(6066);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
