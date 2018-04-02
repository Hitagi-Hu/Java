package Algorithm.backtracking.EightQueens;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class EightQueens extends Application {
    public static final int SIZE = 8;
    private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1};
    @Override
    public void start(Stage primaryStage) {
        search();

        //Display
        GridPane chessBoard = new GridPane();
        chessBoard.setAlignment(Pos.CENTER);
        Label[][] labels = new Label[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                chessBoard.add(labels[i][j] = new Label(), j, i);   //注意GridPane网格顺序是按坐标来的
                //绘制棋盘
                labels[i][j].setStyle("-fx-border-color: black");
                labels[i][j].setPrefSize(55, 55);
            }
        //Display queens
        for (int i = 0; i < SIZE; i++)
            labels[i][queens[i]].setGraphic(new ImageView("images/queen.jpg"));  //棋子

        //create a scene
        Scene scene = new Scene(chessBoard, 55 * SIZE, 55 * SIZE);
        primaryStage.setTitle("EightQueens");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        private boolean search(){
            int k = 0;      //从第一行开始寻找
            while(k >= 0 && k < SIZE){
                int j = findPosition(k); //如果能找到位置，返回该行的位置；否则，返回-1
                if(j < 0){
                    queens[k] = -1; //queens[k] == -1表示找不到，需要回溯(k--)
                    k--;
                }
                else{
                    queens[k] = j;
                    k++;
                }
            }
            return k != -1;

        }
        public int findPosition(int k){
            int start = queens[k] + 1;
            for(int j = start; j < SIZE; j++)
            {
                if(isValid(k, j))
                    return j;
            }
            return -1;
        }
        public boolean isValid(int row, int column){
            for(int i = 1; i <= row; i++)
                // 同列 || 左上角 || 右上角
                if(queens[row - i] == column || queens[row - i] == column - i || queens[row - i] == column + i)
                    return false;
            return true;
        }
        public static void main(String[] args){
        Application.launch(args);
        }
}
