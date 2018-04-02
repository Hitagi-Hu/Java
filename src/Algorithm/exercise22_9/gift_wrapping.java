package Algorithm.exercise22_9;
/*
* 卷包裹法寻找点集的最小凸包
* */
import java.util.ArrayList;
import java.util.Scanner;
import javafx.geometry.Point2D;

public class gift_wrapping {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("How many points are in the set?");
        int number = input.nextInt();
        System.out.print("Enter " + number + " points: ");
        ArrayList<Point2D> set = new ArrayList<>();
        int count = 0;
       while (count++ < number){
           double x = input.nextDouble();
           double y = input.nextDouble();
           set.add(new Point2D(x, y));

       }
       count--;
//       System.out.print(count);
    }

//    public static ArrayList<Point2D> getConvexHull(double[][] s){
//
//    }
}
