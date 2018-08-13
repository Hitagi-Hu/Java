package NetProgram.Greeting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class GreetingClient {
    public static void main(String[] args){
        String serverName = "localhost";
        int port = 6066;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName,port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());

            outToServer.writeUTF("Hello from " + client.getLocalSocketAddress());

            DataInputStream inFromServer = new DataInputStream(client.getInputStream());

            System.out.println("Server says " + inFromServer.readUTF());
            client.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
