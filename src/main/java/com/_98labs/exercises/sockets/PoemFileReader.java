package com._98labs.exercises.sockets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PoemFileReader implements PoemReader {
    private final String filePath;

    public PoemFileReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getLine(int lineNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return readLineFromFile(reader, lineNumber);
        }
    }

    private String readLineFromFile(BufferedReader reader, int lineNumber) throws IOException {
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