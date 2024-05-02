package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
    private static final Logger logger = LogManager.getLogger(Server.class);
    private static ServerSocket serverSocket;
    private final int port;
    private final PoemReader poemReader;
    private volatile boolean isRunning;

    public Server(int port, PoemReader poemReader, ServerSocket serverSocket) {
        this.port = port;
        this.poemReader = poemReader;
        this.isRunning = true;
        Server.serverSocket = serverSocket;
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
        Properties properties;
        try (InputStream inputStream = Server.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties = new Properties();
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Error loading properties file: {}", e.getMessage());
            return;
        }
        int port = Integer.parseInt(properties.getProperty("port"));

        PoemReader poemReader = new PoemFileReader();
        Server server = new Server(port, poemReader, serverSocket);
        server.start();
    }
}