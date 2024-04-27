package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private static final Logger serverLogger = LogManager.getLogger(ClientHandler.class);
    private final Socket clientSocket;
    private final PoemReader poemReader;
    private final Server server;

    public ClientHandler(Socket clientSocket, PoemReader poemReader, Server server) {
        this.clientSocket = clientSocket;
        this.poemReader = poemReader;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            handleClientRequest();
        } catch (IOException e) {
            serverLogger.error("Error handling client request: " + e.getMessage());
        } finally {
            closeClientSocket();
        }
    }

    private void handleClientRequest() throws IOException {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String userInput;
            while ((userInput = in.readLine()) != null) {
                serverLogger.info("Received request from client: {}", userInput);
                if (userInput.equals("00")) {
                    handleClientTermination();
                    break;
                }
                handleUserInput(userInput, out);
            }
        }
    }

    private void handleClientTermination() {
        serverLogger.info("Client terminated the connection.");
        server.stop();
    }

    public void handleUserInput(String userInput, PrintWriter out) throws IOException {
        if (isValidInput(userInput)) {
            int lineNumber = Integer.parseInt(userInput);
            String poemLine = poemReader.getLine(lineNumber);
            out.println(poemLine);
        } else {
            out.println("Send only Integer Number");
        }
    }

    public boolean isValidInput(String userInput) {
        try {
            int lineNumber = Integer.parseInt(userInput);
            return lineNumber > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void closeClientSocket() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            serverLogger.error("Error closing client socket: " + e.getMessage());
        }
    }
}