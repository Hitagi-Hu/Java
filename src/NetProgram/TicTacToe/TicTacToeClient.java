package NetProgram.TicTacToe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TicTacToeClient extends Application implements TicTacToeConstants {
    private boolean myTurn = false;
    //棋子
    private char myToken = ' ';
    private char otherToken = ' ';
    //棋盘
    private Cell[][] cell = new Cell[3][3];
    private Label lblTitle = new Label();
    private Label lblStatus = new Label();

    private int rowSelected;
    private int columnSelected;

    private DataInputStream fromServer;
    private DataOutputStream toServer;

    private boolean continueToPlay = true;
    private boolean waiting = true;

    private String host = "localhost";

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                pane.add(cell[i][j] = new Cell(i, j), j, i);
        ;
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(lblTitle);
        borderPane.setCenter(pane);
        borderPane.setBottom(lblStatus);

        Scene scene = new Scene(borderPane, 320, 350);
        primaryStage.setTitle("TicTacToeClient");
        primaryStage.setScene(scene);
        primaryStage.show();

        connecToServer();
    }

    private void connecToServer() {
        try {
            Socket socket = new Socket(host, 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        new Thread(() -> {
            try {
                int player = fromServer.readInt();

                if (player == PLAYER1) {
                    myToken = 'X';
                    otherToken = 'O';
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 1 with token 'X");
                        lblStatus.setText("Waiting for player 2 to join");
                    });

                    //Received startup notification from server
                    fromServer.readInt(); //Whatever read is ignored
                    Platform.runLater(() -> lblStatus.setText("Player 2 has joined. I start first"));
                    myTurn = true;
                }
                else if (player == PLAYER2){
                    myToken = 'O';
                    otherToken = 'X';
                    Platform.runLater(() -> {
                        lblTitle.setText("Player 2 with token 'O");
                        lblStatus.setText("Waiting for player 1 to move");
                    });
                }

                while (continueToPlay) {
                    if (player == PLAYER1){
                        waitForPlayerAction();
                        sendMove();
                        receivedInfoFromServer();
                    }
                    else if (player == PLAYER2){
                        receivedInfoFromServer();
                        waitForPlayerAction();
                        sendMove();
                    }
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }).start();
    }

    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
            waiting = true;
        }
    }

    private void sendMove() throws IOException {
        toServer.writeInt(rowSelected);
        toServer.writeInt(columnSelected);
    }

    private void receivedInfoFromServer() throws IOException {
        int status = fromServer.readInt();

        if(status == PLAYER1_WON) {
            continueToPlay = false;
            if (myToken == 'X') {
                Platform.runLater(() -> lblStatus.setText("I won! (X)"));
            }
            else if (myToken == 'O') {
                Platform.runLater(() -> lblStatus.setText("Player 1 (X) has won！"));
                receivedMove();
            }
        }
        else if (status == PLAYER2_WON) {
            continueToPlay = false;
            if (myToken == 'O') {
                Platform.runLater(() -> lblStatus.setText("I won! (O)"));
            }
            else if (myToken == 'X'){
                Platform.runLater(() -> lblStatus.setText("Player 2 (O) has won!"));
                receivedMove();
            }
        }
        else if (status == DRAW) {
            continueToPlay = false;
            Platform.runLater(() -> lblStatus.setText("Game is over, no winner!"));

            if (myToken == 'O')
                receivedMove();
        }
        else {
            receivedMove();
            Platform.runLater(() -> lblStatus.setText("My turn"));
            myTurn = true;
        }
    }

    private void receivedMove() throws IOException {
        int row = fromServer.readInt();
        int column = fromServer.readInt();
        Platform.runLater(() -> cell[row][column].setToken(otherToken));
    }
    //Inner class Cell
    private class Cell extends Pane {
        private int row;
        private int column;

        private char token = ' ';

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(2000, 2000);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        public char getToken(){
            return token;
        }
        public void setToken(char token){
            this.token = token;
            repaint();
        }

        protected void repaint(){
            if (token == 'X'){
                Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10, this.getWidth() - 10, 10);
                line2.startXProperty().bind(this.widthProperty().subtract(10));
                line2.startYProperty().bind(this.heightProperty().subtract(10));

                //Add lines to the pane
                this.getChildren().addAll(line1, line2);
            }
            else if (token == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2, this.getHeight() / 2, this.getWidth() / 2 - 10, this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(this.heightProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(this.widthProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);

                getChildren().addAll(ellipse);
            }
        }

        private void handleMouseClick() {
            if (token == ' ' && myTurn) {
                setToken(myToken);
                myTurn = false;
                rowSelected = row;
                columnSelected = column;
                lblStatus.setText("Waiting for the other player to move");
                        waiting = false;
            }
        }
    }
}
