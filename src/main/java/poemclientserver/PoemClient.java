package poemclientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PoemClient {
    public String serverAddress;
    public int port;
    public PoemClient(String serverAddress, int port){
        this.serverAddress = serverAddress;
        this.port = port;
    }
    public void sendMessage(int lineNumber) {
        try {Socket clientSocket = new Socket(serverAddress, port);
            var out = new PrintWriter(clientSocket.getOutputStream(), true);
            var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println(lineNumber);
            String response = in.readLine();
            System.out.println("Response from server");
            System.out.print("Poem's Line: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
