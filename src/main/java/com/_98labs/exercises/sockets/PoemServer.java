package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PoemServer {
    private final String filePath;
    private final int port;
    private boolean isRunning;
    private final Logger logger = LogManager.getLogger(PoemServer.class);
    public PoemServer(String filePath, int port) {
        this.filePath = filePath;
        this.port = port;
        this.isRunning = true;
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server is listening on port " + port);
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Server Connected");
                if (clientSocket != null) {
                    handleClient(clientSocket);
                } else {
                    isRunning = false;
                }
            }
        } catch (IOException e) {
            logger.error("Error starting the server: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) throws IOException {
        try (var out = new PrintWriter(clientSocket.getOutputStream(), true);
             var in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String userInput;
            while ((userInput = in.readLine()) !=null) {
                if (userInput.equals("00")) {
                    logger.info("Client terminated the connection.");
                    isRunning = false;
                    break;
                }
                try {
                    int lineNumber = Integer.parseInt(userInput);
                    String poemLine = getLineFromFile(lineNumber);
                    out.println(poemLine);
                } catch (NumberFormatException e) {
                    out.println("Send only Integer Number");
                }
            }
        } catch (IOException e) {
            logger.error("Error the server: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.error("Error closing the server: " + e.getMessage());
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
