package com._98labs.exercises.sockets;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerTest {
    private Server server;
    private final int testPort = 12345;

    @BeforeEach
    public void setUp() {
        // Create a new Server instance with a mock PoemReader
        server = new Server(testPort, new MockPoemReader());
    }

    @AfterEach
    public void tearDown() {
        // Stop the server after each test
        server.stop();
    }

    @Test
    public void testServerStartAndStop() {
        // Check if the server is initially not running
        assertFalse(server.isRunning());

        // Start the server
        server.start();

        // Check if the server is running after start
        assertTrue(server.isRunning());

        // Stop the server
        server.stop();

        // Check if the server is not running after stop
        assertFalse(server.isRunning());
    }

    // Mock PoemReader implementation for testing
    private static class MockPoemReader implements PoemReader {
        @Override
        public String readPoem() {
            return "Mock Poem Content";
        }
    }
}