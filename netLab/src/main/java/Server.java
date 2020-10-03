import java.net.* ;
import java.io.* ;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private ServerSocket serverSocket;
    private List<Client> clients = new ArrayList<Client>();
    private PrintWriter out;
    private BufferedReader in;

    protected Server() {
        this.start(6666);
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void run() {
        int i = 0;
        while (true) {
            if (serverSocket == null){
                continue;
            } else try {
                if (serverSocket.accept() != null && i != 19) {
                    clients.add(new Client(serverSocket, i));
                    i++;
                }
                if (!clients.isEmpty()) {
                    for (int n = 0; n < clients.size(); n++) {
                        if (clients.get(n).getClientSocket().isClosed()) {
                            clients.remove(n);
                            i--;
                            if (clients.isEmpty()) {
                                this.stopServer();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listen () {
        while (!clients.isEmpty()) {
            if (!clients.isEmpty()) {
                String previousMsg = "";
                for (int n = 0; n < clients.size(); n++) {
                    try {
                        out = new PrintWriter(clients.get(n).getClientSocket().getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(clients.get(n).getClientSocket().getInputStream()));
                        in.readLine();
                        previousMsg = out.toString();
                        out.println(clients.get(n).getUserName() + ":/t" + previousMsg);
                        out.println(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else break;
        }
    }

    public void stopServer() {
        try {
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).getIn().close();
                clients.get(i).getOut().close();
                clients.get(i).getClientSocket().close();
            }
            serverSocket.close();
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public static void main(String[]args) {
//        Server server = new Server();
//        server.listen();
//    }
}
