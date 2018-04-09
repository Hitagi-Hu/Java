package NetProgram.TicTacToe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class TicTacToeServer extends Application implements TicTacToeConstants {
    private int sessionNo = 1;
    @Override
    public void start(Stage primaryStage) {
        TextArea taLog = new TextArea();

        Scene scene = new Scene(new ScrollPane(taLog), 450 , 200);
        primaryStage.setTitle("TicTacToeServer");
        primaryStage.setScene(scene);
        primaryStage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> taLog.appendText(new Date() + ": Server started at socket 8000\n"));
                
                while (true) {
                    //Wait for connection
                    Platform.runLater(() -> taLog.appendText(new Date() + ": Wait for players to join session " + sessionNo + '\n'));
                    
                    //Connect to player 1
                    Socket player1 = serverSocket.accept();
                    Platform.runLater(() -> {
                        taLog.appendText(new Date() + ": Player 1 joined session " + sessionNo + '\n');
                        taLog.appendText("Player 1's IP address: " + player1.getInetAddress().getHostAddress() + '\n');
                    });
                    
                    //Indicate the player is player1
                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    //Connect to player 2
                    Socket player2 = serverSocket.accept();
                    Platform.runLater(() -> {
                        taLog.appendText(new Date() + ": Player 2 joined session " + sessionNo + '\n');
                        taLog.appendText("Player 2's IP address: " + player2.getInetAddress().getHostAddress() + '\n');
                    });

                    //Indicate the player is player2
                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    //Display this session and increment session number
                    Platform.runLater(() -> taLog.appendText(new Date() + ": Started a thread for session " + sessionNo + '\n'));

                    //Launch a new thread for this session of two players
                    new Thread(new HandleASession(player1, player2)).start();
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }).start();
    }
    class HandleASession implements Runnable, TicTacToeConstants {
        private Socket player1;
        private Socket player2;

        private char[][] cell = new char[3][3];  //这里cell只是二维字符矩阵，客户端代码中的cell是(pane型)矩阵
        private DataInputStream fromPlayer1;
        private DataOutputStream toPlayer1;
        private DataInputStream fromPlayer2;
        private DataOutputStream toPlayer2;

        //private  boolean continueToPlay = true;

        public HandleASession(Socket player1, Socket player2) {
            this.player1 = player1;
            this.player2 = player2;

            //Init
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    cell[i][j] = ' ';
        }
        @Override
        public void run(){
            try {
                fromPlayer1 = new DataInputStream(player1.getInputStream());
                toPlayer1 = new DataOutputStream(player1.getOutputStream());
                fromPlayer2 = new DataInputStream(player2.getInputStream());
                toPlayer2 = new DataOutputStream(player2.getOutputStream());

                //Write message to remind player1 to start
                toPlayer1.writeInt(1);

                //Game start
                while (true) {
                    //Receive movie from player1
                    int row = fromPlayer1.readInt();
                    int column = fromPlayer1.readInt();
                    cell[row][column] = 'X';

                    //Check game state
                    if (isWon('X')) {
                        toPlayer1.writeInt(PLAYER1_WON);
                        toPlayer2.writeInt(PLAYER1_WON);
                        sendMove(toPlayer2, row, column);
                        break;
                    }
                    else if (isFull()) { //Draw
                        toPlayer1.writeInt(DRAW);
                        toPlayer2.writeInt(DRAW);
                        sendMove(toPlayer2, row, column);
                        break;
                    }
                    else {
                        toPlayer2.writeInt(CONTINUE);
                        sendMove(toPlayer2, row, column);
                    }

                    //Receive movie from player2
                    row = fromPlayer2.readInt();
                    column = fromPlayer2.readInt();
                    cell[row][column] = 'O';

                    //Check game state
                    if(isWon('O')) {
                        toPlayer1.writeInt(PLAYER2_WON);
                        toPlayer2.writeInt(PLAYER2_WON);
                        sendMove(toPlayer1, row, column);
                        break;
                    }
                    /**棋盘位置个数为奇数，不可能在player2落子时棋盘为满*/
                   /* else if (isFull()) { //Draw
                        toPlayer1.writeInt(DRAW);
                        toPlayer2.writeInt(DRAW);
                        sendMove(toPlayer1, row, column);
                    }*/
                    else {
                        toPlayer1.writeInt(CONTINUE);
                        sendMove(toPlayer1, row, column);
                    }
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //send the step to another player
        private void sendMove(DataOutputStream output, int row, int column) throws IOException {
            output.writeInt(row);
            output.writeInt(column);
        }

        private boolean isWon(char c) {
            //check all rows
            for (int i = 0; i < 3; i++)
                if (cell[i][0] == c && cell[i][1] == c && cell[i][2] == c)
                    return true;
            //check all columns
            for (int j = 0; j < 3; j++)
                if (cell[0][j] == c && cell[1][j] == c && cell[2][j] == c)
                    return true;
            //check diagonal(对角线)
            if (cell[0][0] == c && cell[1][1] == c && cell[2][2] == c)
                return true;
            if (cell[0][2] == c && cell[1][1] == c && cell[2][0] == c)
                return true;
            return false;
        }

        private boolean isFull() {
            for (char[] chars : cell)
                for (char c : chars)
                    if (c == ' ')
                        return false;
            return true;
        }


    }
}
