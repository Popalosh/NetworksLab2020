
import java.util.Scanner;

public class StartUp {
    public static void main(String[] args) {
            Client client = new Client();
            String ip = "127.0.0.1";
            int port = 6666;
            client.startConnection(ip, port);;
            Scanner scanner = new Scanner(System.in);
            System.out.println(client.sendMessage(scanner.next()));
            scanner.close();
    }
}
