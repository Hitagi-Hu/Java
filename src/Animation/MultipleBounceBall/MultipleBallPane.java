package MultipleBounceBall;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;



public class MultipleBallPane extends Pane {
    private Timeline animation;
    public MultipleBallPane(){
        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    //增减球的数量
    public void add(){
        Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
        getChildren().add(new Ball(30, 30, 20, color));
    }
    public void sub(){
        if(getChildren().size() > 0)
            getChildren().remove(getChildren().size() - 1);
    }

    public void play(){
        animation.play();
    }
    public void pause(){
        animation.pause();
    }
    public void increaseSpeed(){
        animation.setRate(animation.getRate() + 0.1);
    }

    public void decreaseSpeed(){
        animation.setRate((animation.getRate() > 0 ? animation.getRate() - 0.1 : 0));

    }

    public DoubleProperty rateProperty(){
        return  animation.rateProperty();
    }

    //球的运动
    public void moveBall(){
       for (Node node: this.getChildren()){
           Ball ball = (Ball)node;
           if (ball.getCenterX() < ball.getRadius() || ball.getCenterX() > getWidth() - ball.getRadius())
                ball.dx *= -1;
           if (ball.getCenterY() < ball.getRadius() || ball.getCenterY() > getHeight() - ball.getRadius())
                ball.dy *= -1;
           ball.setCenterX(ball.dx + ball.getCenterX());
           ball.setCenterY(ball.dy + ball.getCenterY());

       }

    }



}
