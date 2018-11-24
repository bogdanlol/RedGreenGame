package proiectparcredgreen;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RedGreenClient {
    private JFrame frame = new JFrame("Red Green");
    private JLabel mesaj = new JLabel("");
    private ImageIcon icon;
    private ImageIcon opponentIcon;
    private Square[] tabela = new Square[20];
    private Square currentSquare;
    private static int PORT = 8291;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
   
    public RedGreenClient(String serverAddress) throws Exception {
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        mesaj.setBackground(Color.lightGray);
        frame.getContentPane().add(mesaj, "South");
        JPanel boardPanel = new JPanel();
        boardPanel.setBackground(Color.orange);
        boardPanel.setLayout(new GridLayout(5, 4, 5, 5));
       
        for (int i = 0; i < tabela.length; i++) {
        	final int j = i;
            tabela[i] = new Square();
            tabela[i].addMouseListener(new MouseAdapter() {
            	public void mousePressed(MouseEvent e) {
            		currentSquare = tabela[j];
            	out.println("MOVE " + j);    }});
            		boardPanel.add(tabela[i]);
              		
        }  
        frame.getContentPane().add(boardPanel, "Center");
    }

    public void play() throws Exception {
        String rspns;
        try {
            rspns = in.readLine();
            if (rspns.startsWith("WELCOME")) {
                char semn = rspns.charAt(8);
                icon = new ImageIcon(semn == 'G' ? "content/Green.png" : "content/Red.png");
                opponentIcon  = new ImageIcon(semn == 'G' ? "content/Red.png" : "content/Green.png");
                frame.setTitle("Player " + semn);
             
            }
            while (true) {
                rspns = in.readLine();
                if (rspns.startsWith("VALID_MOVE")) {
                currentSquare.setIcon(icon);
                currentSquare.repaint();
                    mesaj.setText("Miscare valida,asteptati..");
                   
                }
                else if (rspns.startsWith("OPPONENT_MOVED")) {
                    int loc = Integer.parseInt(rspns.substring(15));
                    tabela[loc].setIcon(opponentIcon);
                    tabela[loc].repaint();
                    
                    mesaj.setText("Opponent si-a facut miscare, randul tau");
                } else if (rspns.startsWith("VICTORY")) {
                    mesaj.setText("Ai castigat");
                    break;
                } else if (rspns.startsWith("DEFEAT")) {
                    mesaj.setText("Ai pierdut");
                    break;
                } else if (rspns.startsWith("TIE")) {
                    mesaj.setText("Ai facut egal");
                    break;
                } else if (rspns.startsWith("MESSAGE")) {
                    mesaj.setText(rspns.substring(8));
                }
            }
            out.println("QUIT");
        }
        finally {
            socket.close();
        }
    }

    private boolean pAgain() {
        int rspns = JOptionPane.showConfirmDialog(frame,
            "Play Again?",
            "RGB",
            JOptionPane.YES_NO_OPTION);
        frame.dispose();
        return rspns == JOptionPane.YES_OPTION;
    }

    
    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            RedGreenClient client = new RedGreenClient(serverAddress);
            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
            client.frame.setSize(400, 400);
            client.frame.setVisible(true);
            client.frame.setResizable(false);
            client.play();
            if (!client.pAgain()) {
                break;
            }
        }
    }
}