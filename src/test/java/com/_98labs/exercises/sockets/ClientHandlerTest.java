package com._98labs.exercises.sockets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientHandlerTest {

    @Mock
    private Socket mockClientSocket;

    @Mock
    private PoemReader mockPoemReader;

    @Mock
    private Server mockServer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleUserInput_ValidInput_ShouldSendPoemLine() throws IOException {
        // Arrange
        String userInput = "3";
        String expectedPoemLine = "Third line of poem";
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockPoemReader.getLine(3)).thenReturn(expectedPoemLine);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, mockPoemReader, mockServer);

        // Act
        clientHandler.handleUserInput(userInput, mockPrintWriter);

        // Assert
        verify(mockPoemReader).getLine(3);
        verify(mockPrintWriter).println(expectedPoemLine);
    }

    @Test
    void handleUserInput_InvalidInput_ShouldSendErrorMessage() {
        // Arrange
        String userInput = "abc";
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, mockPoemReader, mockServer);

        // Act
        try {
            clientHandler.handleUserInput(userInput, mockPrintWriter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Assert
        verify(mockPrintWriter).println("Send only Integer Number");
    }

    @Test
    void isValidInput_ValidInput_ShouldReturnTrue() {
        // Arrange
        String userInput = "123";
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, mockPoemReader, mockServer);

        // Act
        boolean isValid = clientHandler.isValidInput(userInput);

        // Assert
        assertTrue(isValid);
    }

    @Test
    void isValidInput_InvalidInput_ShouldReturnFalse() {
        // Arrange
        String userInput = "abc";
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, mockPoemReader, mockServer);

        // Act
        boolean isValid = clientHandler.isValidInput(userInput);

        // Assert
        assertFalse(isValid);
    }
}