package MultipleBounceBall;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    public double dx = 1;
    public double dy = 1;
    public Ball(double x, double y, double radius, Color color) {
        super(x, y, radius);
        setFill(color);
    }

}
