package com._98labs.exercises.sockets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PoemFileReader implements PoemReader {
    private final String[] poemLines;

    public PoemFileReader() {
        this.poemLines = loadPoemLines();
    }

    @Override
    public String getLine(int lineNumber) {
        if (lineNumber < 1 || lineNumber > poemLines.length) {
            return "Line not found";
        }
        return poemLines[lineNumber - 1]; // Adjusting index to start from 0
    }

    private String[] loadPoemLines() {
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFilePath()))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Error reading poem file: " + e.getMessage(), e);
        }
    }

    private String loadFilePath() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                String poemFilePath = properties.getProperty("filePath2");
                return System.getProperty("user.dir") + poemFilePath;
            } else {
                throw new RuntimeException("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties: " + e.getMessage(), e);
        }
    }
}

/*package com._98labs.exercises.sockets;

import java.io.*;
import java.util.Properties;

public class PoemFileReader implements PoemReader {
    private final String loadFilePath;

    public PoemFileReader() {
        this.loadFilePath = loadFilePath();
    }

    @Override
    public String getLine(int lineNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFilePath))) {
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

    private String loadFilePath() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                String poemFilePath = properties.getProperty("filePath");
                return System.getProperty("user.dir") + poemFilePath;
            } else {
                throw new RuntimeException("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties: " + e.getMessage(), e);
        }
    }
}

 */