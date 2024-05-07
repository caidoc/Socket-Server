package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PoemFileReader implements PoemReader {
    private static final Logger logger = LogManager.getLogger(PoemFileReader.class);
    private final String loadFilePath;
    private final boolean eagerLoading;
    private final List<String> cachedLines;

    public PoemFileReader() {
        Properties properties = loadProperties();
        this.loadFilePath = loadFilePath(properties);
        this.eagerLoading = Boolean.parseBoolean(properties.getProperty("eagerLoading"));
        this.cachedLines = new ArrayList<>();
        if (eagerLoading) {
            try {
                cachePoemLines();
            } catch (IOException e) {
                logger.error("Error caching poem lines: " + e.getMessage(), e);
            }
        }
    }

    @Override
    public String getLine(int lineNumber) throws IOException {
        if (eagerLoading) {
            return eagerLoadingGetLine(lineNumber);
        } else {
            return lazyLoadingGetLine(lineNumber);
        }
    }

    private String eagerLoadingGetLine(int lineNumber) {
            if (lineNumber >= 1 && lineNumber <= cachedLines.size()) {
                return cachedLines.get(lineNumber - 1);
            } else {
                return "Line not found";
                //logger.info("Line not found");
            }
    }

    private String lazyLoadingGetLine(int lineNumber) throws IOException {
            if (lineNumber >= 1 && lineNumber <= cachedLines.size()) {
                return cachedLines.get(lineNumber - 1);
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(loadFilePath))) {
                    return readLineFromFile(reader, lineNumber);
                }
            }
    }

    private String readLineFromFile(BufferedReader reader, int lineNumber) throws IOException {
        String poemLine;
        int currentLine = 0;
        while ((poemLine = reader.readLine()) != null) {
            currentLine++;
            if (currentLine == lineNumber) {
                cachedLines.add(poemLine); // Cache the line
                return poemLine;
            }
        }
        return "Line not found";
    }

    private void cachePoemLines() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(loadFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cachedLines.add(line);
            }
        }
    }

    private Properties loadProperties() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
                return properties;
            } else {
                logger.error("config.properties file not found in classpath");
            }
        } catch (IOException e) {
            logger.error("Error loading config.properties: " + e.getMessage(), e);
        }
        return null;
    }

    private String loadFilePath(Properties properties) {
        String poemFilePath = properties.getProperty("filePath");
        return System.getProperty("user.dir") + poemFilePath;
    }
}