import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ChatApp {

    public static void main(String[] args) throws IOException {
        List<Socket> clients = Collections.synchronizedList(new ArrayList<>());
        ServerSocket server = new ServerSocket(5555);
        GiveMeMoney skarbonka = new GiveMeMoney();
        //pętla do obsługi wielu klientów
        while(true) {
            Socket client = server.accept();
            clients.add(client);
            new Thread(() -> {
                try {
                    Scanner input = new Scanner(client.getInputStream());
                    PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                    String nick ="anonymous";
                    while (input.hasNextLine()) {
                        String clientRequest = input.nextLine();
                        //rozłączenie z serwerem
                        if (clientRequest.toLowerCase().equals("exit")){
                            output.println("Connection closed!!!");
                            break;
                        }
                        //nadanie nick'a przez użytkownika
                        if (clientRequest.toLowerCase().startsWith("nick") && clientRequest.split(" ").length > 1){
                            nick = clientRequest.split(" ")[1];
                            output.println("Your nick is now: " + nick);
                            continue;
                        }
                        //dodaj kwotę
                        if (clientRequest.toLowerCase().startsWith("money") && clientRequest.split(" ").length == 2){
                            String moneyStr = clientRequest.split(" ")[1];
                            //dodać obsługę wyjątku
                            BigDecimal money = new BigDecimal(moneyStr);
                            skarbonka.addAmount(money);
                            output.println("Your payment is: " + moneyStr+ ". Now amount is: " + skarbonka.getAmountAsString());
                        }
                        //rozesłanie wiadomości do pozostałych klientów
                        if (clientRequest.toLowerCase().startsWith("text") && clientRequest.split(" ").length > 1){
                            for(Socket socket: clients){
                                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                                String message = clientRequest.substring(5);
                                out.println(nick+": " + message);
                            }
                        }


                    }
                    clients.remove(client);
                } catch (IOException e){

                }
            }).start();
        }
    }
}
