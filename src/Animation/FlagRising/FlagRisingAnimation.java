package FlagRising;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;


public class FlagRisingAnimation extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane pane = new BorderPane();
        //String imagePath = "image/flag.cn.jpg";
        //Image image = new Image("image/flag.cn.jpg");
        ImageView imageView = new ImageView("image/flag.cn.jpg");
        pane.setBottom(imageView);


        PathTransition pt = new PathTransition(Duration.millis(10000), new Line(200, 200, 200, 50), imageView);
        pt.setCycleCount(5);
        //pt.play();

        //event
        pane.setOnMouseClicked(e ->{
            if(e.getButton() == MouseButton.PRIMARY)
                pt.play();
            else if(e.getButton() == MouseButton.SECONDARY)
                pt.pause();
        });


        Scene scene = new Scene(pane, 400, 250);
        primaryStage.setTitle("FlagRisingAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();

    }
    public static void main(String[] args){
        launch(args);

    }
}
