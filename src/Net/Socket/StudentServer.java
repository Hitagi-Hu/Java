package Net.Socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class StudentServer {
    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;

    public static void main(String[] args) {
        new StudentServer();
    }

    public StudentServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started at " + new Date() + '\n');

            outputToFile = new ObjectOutputStream(new FileOutputStream("student.dat", true));

            while (true) {
                Socket socket = serverSocket.accept(); //Listen
                inputFromClient = new ObjectInputStream(socket.getInputStream());

                Object o = inputFromClient.readObject();

                outputToFile.writeObject(o);
                System.out.println("A new student object is stored");
            }
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                inputFromClient.close();
                outputToFile.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}