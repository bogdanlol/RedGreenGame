package proiectparcredgreen;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class RedGreenServer {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8291);
        System.out.println("Red Green Server Running");
        try {
            while (true) {
                Game game = new Game();
                Player playerGreen = new Player(listener.accept(), 'G');
                Player playerRed = new Player(listener.accept(), 'R');
                playerGreen.setOpponent(playerRed);
                playerRed.setOpponent(playerGreen);
                Game.currentPlayer = playerGreen;
                playerGreen.start();
                playerRed.start();
            }
        } finally {
            listener.close();
        }
    }
}
    
