import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FlashText extends Application {
    private String text = "";

    @Override
    public void start(Stage primaryStage){
        StackPane pane = new StackPane();
        Label lblText = new Label("Java");
        pane.getChildren().addAll(lblText);

        new Thread(() -> {
            try{
                while (true){
                    if(lblText.getText().trim().length() == 0)
                        text = "Welcome";
                    else
                        text = "";

                    Platform.runLater(() -> lblText.setText(text));

                    Thread.sleep(1000);

                }
            }
            catch (InterruptedException ex){}
        }
        ).start();


        //Create a scene and show
        Scene scene = new Scene(pane, 200, 50);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
