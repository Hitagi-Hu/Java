package NetProgram.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultiThreadServer extends Application {
    private TextArea ta = new TextArea();
    private int clientNo = 0;
    @Override
    public void start(Stage primaryStage){
        //Create a scene for displaying
        Scene scene = new Scene(new ScrollPane(ta), 450, 200);
        primaryStage.setTitle("MultiThreadServer");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                //Create server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                ta.appendText("MultiThreadServer started at " + new Date()+ '\n');

                //死循环监听客户端请求，
                while (true){
                    //Listen for client request
                    Socket socket = serverSocket.accept();

                    clientNo++;
                    Platform.runLater(() -> {
                        ta.appendText("Starting thread for client " + clientNo + " at" + new Date() + '\n');

                        //Find the client's host name and IP address
                        InetAddress inetAddress = socket.getInetAddress();
                        ta.appendText("Client " + clientNo + "'s host name is " + inetAddress.getHostName() + '\n');
                        ta.appendText("Client " + clientNo + "'s IP address is " + inetAddress.getHostAddress() + '\n');
                    });

                    //Create a new thread for a new connection
                    new Thread(new HandleAClient(socket)).start();
                }
            }
            catch (IOException ex){
                System.err.println(ex);
            }
        }).start();
    }

    //Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket;
        public HandleAClient(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

                //Continuously serve the client
                while (true) {
                    double radius = inputFromClient.readDouble();

                    double area = radius * radius * Math.PI;

                    outputToClient.writeDouble(area);

                    Platform.runLater(() -> {
                        ta.appendText("Radius received form client: " + radius + '\n');
                        ta.appendText("Area found: " + area + '\n');
                    });
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
