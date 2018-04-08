package MySQL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;



public class FindStudent extends Application {
    private Statement statement;
    private TextField tfSSN = new TextField();
    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage){
        initializeDB();

        Button button = new Button("Search");
        HBox hBox = new HBox(3);
        hBox.getChildren().addAll(new Label("SSN"), tfSSN, button);
        VBox vBox = new VBox(1);
        vBox.getChildren().addAll(hBox,lblStatus);
        tfSSN.setPrefColumnCount(6);
        button.setOnAction(e -> search());
        vBox.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER)
                search();
        });

        Scene scene = new Scene(vBox, 420,80);
        primaryStage.setTitle("FindStudent");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void search(){
        String ssn = tfSSN.getText();
        try {
            String query = "select name, age from student where ssn = '" + ssn + " '";
            ResultSet result = statement.executeQuery(query);

            if (result.next()) {
                String name = result.getString(1);
                String age = result.getString(2);
                lblStatus.setText(name + " " + age);
            }
            else
                lblStatus.setText("Not found");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void initializeDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            //Establish a connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "Hitagi", "123456");
            System.out.println("Database connected");

            statement = connection.createStatement();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
