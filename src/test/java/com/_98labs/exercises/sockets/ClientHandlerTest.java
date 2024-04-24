package com._98labs.exercises.sockets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClientHandlerTest {
    private ServerSocket serverSocket;
    private Thread serverThread;
    private int port;
    private ClientHandler clientHandler;

    @BeforeEach
    void setup() throws IOException {
        serverSocket = new ServerSocket(12345);
        port = serverSocket.getLocalPort();
        serverThread = new Thread(() -> {
            try {
                Socket clientSocket = serverSocket.accept();
                PoemReader poemReader = new PoemReader() {
                    @Override
                    public String getLine(int lineNumber) throws IOException {
                        return null;
                    }
                }; // You need to implement this class according to your requirements
                Server server = new Server(); // You need to implement this class according to your requirements
                clientHandler = new ClientHandler(clientSocket, poemReader, server);
                clientHandler.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        serverSocket.close();
        clientHandler = null;
        serverThread.interrupt();
    }

    @Test
    void testClientHandlerWithValidInput() throws IOException {
        Socket socket = new Socket("localhost", port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("1");
        String response = reader.readLine();
        assertEquals("First line of the poem", response);
    }

    @Test
    void testClientHandlerWithInvalidInput() throws IOException {
        Socket socket = new Socket("localhost", port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("abc");
        String response = reader.readLine();
        assertEquals("Send only Integer Number", response);
    }

    @Test
    void testClientHandlerWithClientTerminatingConnection() throws IOException {
        Socket socket = new Socket("localhost", port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

        writer.println("00");
        assertTrue(socket.isClosed());
    }
}