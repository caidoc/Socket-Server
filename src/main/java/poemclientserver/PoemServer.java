package poemclientserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PoemServer {
    private final String filePath;
    private final int port;
    public PoemServer(String filePath, int port) {
        this.filePath = filePath;
        this.port = port;
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            boolean isRunning = true;
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server Connected");
                if (clientSocket != null) {
                    handleClient(clientSocket);
                } else {
                    isRunning = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting the server: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        try (var out = new PrintWriter(clientSocket.getOutputStream(), true);
             var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String userInput;
            while ((userInput = in.readLine()) !=null) {
                if (userInput.equals("00")) {
                    System.out.println("Client terminated the connection.");
                    break;
                }
                int lineNumber = Integer.parseInt(userInput);
                String poemLine = getLineFromFile(lineNumber);
                out.println(poemLine);
            }
        } catch (IOException e) {
            System.err.println("Error the server: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing the server: " + e.getMessage());
            }
        }
    }

    private String getLineFromFile(int lineNumber) throws IOException {
        try (var reader = new BufferedReader(new FileReader(filePath))) {
            String poemLine;
            int currentLine = 0;
            while ((poemLine = reader.readLine()) != null) {
                currentLine++;
                if (currentLine == lineNumber) {
                    return poemLine;
                }
            }
            return "Line not found";
        }
    }

}
