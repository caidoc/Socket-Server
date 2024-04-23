package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger serverLogger = LogManager.getLogger(Server.class);
    private PoemReader poemReader = null;
    private boolean isRunning = true;

    public Server(PoemReader poemReader) {
        this.poemReader = poemReader;
    }

    public Server() {
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverLogger.info("Server is listening on port " + port);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                serverLogger.info("Server Connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket, poemReader, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            serverLogger.error("Error starting the server: " + e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        int port = 12345;
        PoemReader poemReader = new PoemFileReader("C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt");
        Server server = new Server(poemReader);
        server.start(port);
    }

    public boolean isRunning() {
        return false;
    }
}