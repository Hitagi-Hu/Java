package Timeline;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TimelineDemo extends Application {
    @Override
    public void start(Stage primaryStage){
        StackPane pane = new StackPane();
        Text text = new Text(20, 50, "Make it cool");
        text.setFill(Color.RED);
        pane.getChildren().add(text);

        //event handler for changing text
        EventHandler<ActionEvent> eventHandler = e -> {
            if(text.getText().length() != 0) {
                text.setText("");
            }
            else{
                text.setText("Make it cool");
            }
        };

        //animation
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        //control
        text.setOnMouseClicked(e -> {
            if(animation.getStatus() == Animation.Status.PAUSED) {
                animation.play();
            }
            else{
                animation.pause();
            }
        });
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("TimelineDemo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private static void main(String[] args){
        launch(args);
    }
}
