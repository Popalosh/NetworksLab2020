import java.net.* ;
import java.io.* ;
import java.util.Scanner;

public class ClientApp {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            Socket clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Something went wrong";
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
        String ip = "127.0.0.1";
        int port = 6666;
        clientApp.startConnection(ip, port);;
        Scanner scanner = new Scanner(System.in);
        System.out.println(clientApp.sendMessage(scanner.next()));
        System.out.println(clientApp.in);
    }
}
