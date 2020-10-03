import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client{

    protected Socket clientSocket;

    protected PrintWriter out;
    private BufferedReader in;

    public int getUserName() {
        return userName;
    }

    protected int userName;

    public Client(ServerSocket socket, int i) {
        try {
            this.clientSocket = socket.accept();
            this.out = new PrintWriter(clientSocket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.userName = i;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }

}
