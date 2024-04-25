package com._98labs.exercises.sockets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoemFileReaderTest {
    private PoemFileReader poemFileReader;

    @BeforeEach
    public void setUp() {
        // Initialize a PoemFileReader instance with a temporary file
        poemFileReader = new PoemFileReader("C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt");
    }
    @Test
    public void testGetLine() throws IOException {
        // Test reading the first line
        assertEquals("Still I Rise", poemFileReader.getLine(1));
        // Test reading the second line
        assertEquals("BY MAYA ANGELOU", poemFileReader.getLine(2));
        // Test reading the third line
        assertEquals("You may write me down in history", poemFileReader.getLine(3));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(55));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(1000000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(-1000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(0));
    }
}
