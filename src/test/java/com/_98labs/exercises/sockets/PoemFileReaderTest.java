package com._98labs.exercises.sockets;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PoemFileReaderTest {
    @Test
    public void testEagerLoading() throws IOException {
        PoemFileReader poemFileReader = new PoemFileReader();
        // Test loading first line
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

    @Test
    public void testLazyLoading() throws IOException {
        // Modify config.properties to enable lazy loading
        System.setProperty("config.properties", "eagerLoading=false");

        PoemFileReader poemFileReader = new PoemFileReader();
        // Test loading first line
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

/*
    @Test
    public void testEagerLoading() throws IOException {
        PoemFileReader poemFileReader = new PoemFileReader();
        // Test reading the first line
        assertEquals("One Art", poemFileReader.getLine(1));
        // Test reading the second line
        assertEquals("BY ELIZABETH BISHOP", poemFileReader.getLine(2));
        // Test reading the third line
        assertEquals("The art of losing isn’t hard to master;", poemFileReader.getLine(3));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(55));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(1000000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(-1000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(0));
    }

    @Test
    public void testLazyLoading() throws IOException {
        // Modify config.properties to enable lazy loading
        System.setProperty("config.properties", "eagerLoading=false");

        PoemFileReader poemFileReader = new PoemFileReader();
        // Test reading the first line
        assertEquals("One Art", poemFileReader.getLine(1));
        // Test reading the second line
        assertEquals("BY ELIZABETH BISHOP", poemFileReader.getLine(2));
        // Test reading the third line
        assertEquals("The art of losing isn’t hard to master;", poemFileReader.getLine(3));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(55));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(1000000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(-1000));
        // Test reading a non-existent line
        assertEquals("Line not found", poemFileReader.getLine(0));
    }

 */

}
