import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Online extends Game{
    
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Online(Player player, Scanner scan) {
        super(player, scan);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void game() {

    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String greeting = in.readLine();
            if ("hello server".equals(greeting)) {
                System.out.println("hello client");
                out.println("hello client");
            } else {
                System.out.println("unrecognised greeting");
                out.println("unrecognised greeting");
            }
        } catch (Exception e) {
            System.out.println("Exception occured in start(): " + e);
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Exception occured in stop(): " + e);
        }
        
    }

    @Override
    public void newRound() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'newRound'");
    }
    
}
