package personalised.library.GraphDB;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import personalised.library.GraphDB.SimpleGraph;

public class Server {

    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private SimpleGraph g;

    public Server(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        g = new SimpleGraph("" + port + ".db");
    }

    public void start() throws IOException{
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            line = line.toLowerCase();
            if (line.startsWith("commit")) {
                g.commit();
            } else if (line.startsWith("add")) {
                g.add();
            } else if (line.startsWith("map")) {
                g.map();
            } else if (line.startsWith("list")) {
                g.list();
            } else if (line.startsWith("value")) {
                g.value();
            } else if (line.startsWith("is")) {
                g.is();
            } else if (line.startsWith("triples")) {
                g.triples();
            } else if (line.startsWith("isSubject")) {
                g.isSubject();
            } else if (line.startsWith("isPredicate")) {
                g.isPredicate();
            } else if (line.startsWith("isObject")) {
                g.isObject();
            } else if (line.startsWith("query")) {
                g.query();
            }
        }
    }

    public static void main(String args[]){
        try {
            Server s = new Server(1111);
            s.start();
        } catch (IOException e){
            System.out.println(e);
        }
    }

}