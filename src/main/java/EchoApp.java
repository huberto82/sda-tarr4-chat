import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EchoApp {
    static Logger logger = Logger.getLogger("EchoApp");
    public static void main(String[] args) {
        logger.setLevel(Level.ALL);
        try {
            FileHandler handler = new FileHandler("chat.log");
            handler.setFormatter( new SimpleFormatter());
            logger.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            ServerSocket server = new ServerSocket(3333);
            logger.log(Level.INFO,"Server waiting for connection ...");
            Socket client = server.accept();
            logger.log(Level.INFO,"Client connected from: " + client.getInetAddress());
            //połączenie zestawione
            //z obiektu input odczytujemy dane klienta
            Scanner input = new Scanner(client.getInputStream());
            //do obiektu output wysyłamy dane do klienta
            PrintWriter ouput = new PrintWriter(client.getOutputStream(), true);
            while(input.hasNext()){
                //odczytujemy łańcuch od klienta
                String clientRequest = input.nextLine();
                //wysyłamy do klienta odpowiedź
                ouput.println("ECHO FROM SERVER: " + clientRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.log(Level.INFO, "Client close connection");
    }
}
