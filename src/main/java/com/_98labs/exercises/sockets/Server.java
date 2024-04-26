package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private static ServerSocket serverSocket;
    private final int port;
    private final PoemReader poemReader;
    private volatile boolean isRunning;

    public Server(int port, PoemReader poemReader, ServerSocket serverSocket) {
        this.port = port;
        this.poemReader = poemReader;
        this.serverSocket = serverSocket;
        this.isRunning = false;
    }

    public void start() {
        isRunning = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Server is listening on port {}", port);

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Server connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket, poemReader, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            logger.error("Error starting the server: {}", e.getMessage());
        }
    }

    public void stop() {
        isRunning = false;
    }

    public static void main(String[] args) {
        int port = 12345;
        String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt";
        PoemReader poemReader = new PoemFileReader(filePath);
        Server server = new Server(port, poemReader, serverSocket);
        server.start();
    }

    public boolean isRunning() {
        return isRunning;
    }
}