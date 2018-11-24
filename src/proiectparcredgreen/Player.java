package proiectparcredgreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import proiectparcredgreen.Game;

class Player extends Thread {
    char semn;
    Player opponent;
    Socket socket;
    BufferedReader in;
    PrintWriter out;
  
    public Player(Socket socket, char mark) {
        this.socket = socket;
        this.semn = mark;
   
      
        try {
            in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("WELCOME " + mark);
            out.println("MESSAGE Asteptand pentru un oponent");
        } catch (IOException e) {
            System.out.println("Player down : " + e);
        }
    }

 
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

 
    public void otherPlayerMoved(int location) {
       out.println("OPPONENT_MOVED " + location);
        out.println(
            Game.castigator() ? "DEFEAT" : Game.tabelaPlina() ? "TIE" : "");
    }
    
    public void run() {
    	
        try {
           
            out.println("MESSAGE Jucatori Conectati");
            if (semn == 'G') {
                out.println("MESSAGE Miscarea curenta");
            }
            while (true) {
                String command = in.readLine();
                if (command.startsWith("MOVE")) {
                    int location = Integer.parseInt(command.substring(5));              
                    	if(Game.legalMove(location, this)){
                        out.println("VALID_MOVE");
                        out.println(Game.castigator() ? "VICTORY"
                                     : Game.tabelaPlina() ? "TIE"
                                     : "");  
                    }
                    else {
                        out.println("MESSAGE ?");
                    }
                } else if (command.startsWith("QUIT")) {
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("Player down: " + e);
        } finally {
            try {socket.close();} catch (IOException e) {}
        }
    }}