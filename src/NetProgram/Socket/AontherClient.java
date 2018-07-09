package NetProgram.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AontherClient extends Application {
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;

    @Override
    public void start(Stage primaryStage){
        FlowPane paneForTextField = new FlowPane();
        paneForTextField.setPadding(new Insets(5, 5, 5, 5));
        paneForTextField.setHgap(5);
        paneForTextField.setVgap(5);
        paneForTextField.setStyle("-fx-border-color: green");


        TextField tf = new TextField();
        tf.setAlignment(Pos.BOTTOM_RIGHT);
        paneForTextField.getChildren().addAll(new Label("Enter a radius: "), tf);


        BorderPane mainPane = new BorderPane();
        //Text area to display contents
        TextArea ta = new TextArea();
        mainPane.setCenter(new ScrollPane(ta));
        mainPane.setTop(paneForTextField);

        Scene scene = new Scene(mainPane, 450, 200);
        primaryStage.setTitle("AontherClient");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            Socket socket = new Socket("localhost", 8000);
            System.out.println("local port: " + socket.getLocalPort()); //在控制台打印端口信息
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex){
            ta.appendText(ex.toString() + '\n');
        }

        tf.setOnAction(e -> {
            try {
                double radius = Double.parseDouble(tf.getText().trim());

                toServer.writeDouble(radius);
                toServer.flush();

                double area =fromServer.readDouble();

                ta.appendText("Radius is " + radius + '\n');
                ta.appendText("Area received from the server is " + area + '\n');
            }
            catch (IOException ex){
                System.err.println(ex);
            }
        });


    }
}

