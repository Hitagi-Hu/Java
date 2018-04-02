package BounceBallcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class BounceBallControl extends Application {
    @Override
    public void start(Stage primaryStage){
        BallPane ballPane = new BallPane();

        ballPane.setOnMousePressed(e -> ballPane.pause());
        ballPane.setOnMouseReleased(e -> ballPane.play());

        ballPane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP)
                ballPane.increaseSpeed();
            else if(e.getCode() == KeyCode.DOWN)
                ballPane.decreaseSpeed();
        });

        primaryStage.setTitle("BounceBallControl");
        primaryStage.setScene(new Scene(ballPane, 250, 150));
        primaryStage.show();
        ballPane.requestFocus();
    }
}
