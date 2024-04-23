package com._98labs.exercises.sockets;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServerTest {

    @Test
    public void testStartAndStop() {
        PoemReader poemReader = new PoemFileReader("C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt");
        Server server = new Server(poemReader);

        // Start the server
        server.start(12345);

        // Stop the server
        server.stop();

        // Check if the server is no longer running
        assertFalse(server.isRunning());
    }
}