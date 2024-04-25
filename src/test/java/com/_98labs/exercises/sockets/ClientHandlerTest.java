package com._98labs.exercises.sockets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.PrintWriter;
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
        String userInput = "1";
        String expectedPoemLine = "Still I Rise";
        PrintWriter mockPrintWriter = mock(PrintWriter.class);
        when(mockPoemReader.getLine(1)).thenReturn(expectedPoemLine);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, mockPoemReader, mockServer);

        // Act
        clientHandler.handleUserInput(userInput, mockPrintWriter);

        // Assert
        verify(mockPoemReader).getLine(1);
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
    void isValidInput_ValidInteger_ReturnsTrue() {
        // Arrange
        ClientHandler clientHandler = new ClientHandler(null, null, null);
        String validInput = "123";

        // Act
        boolean isValid = clientHandler.isValidInput(validInput);

        // Assert
        assertTrue(isValid, "Expected valid input to return true");
    }
    @Test
    void isValidInput_InvalidRationalNumbers_ReturnsFalse() {
        // Arrange
        ClientHandler clientHandler = new ClientHandler(null, null, null);
        String invalidInput = "1.5";

        // Act
        boolean isValid = clientHandler.isValidInput(invalidInput);

        // Assert
        assertFalse(isValid, "Expected invalid input to return false");
    }

    @Test
    void isValidInput_StringInput_ReturnsFalse() {
        // Arrange
        ClientHandler clientHandler = new ClientHandler(null, null, null);
        String invalidInput = "abc";

        // Act
        boolean isValid = clientHandler.isValidInput(invalidInput);

        // Assert
        assertFalse(isValid, "Expected invalid input to return false");
    }

    @Test
    void isValidInput_NullInput_ReturnsFalse() {
        // Arrange
        ClientHandler clientHandler = new ClientHandler(null, null, null);
        String nullInput = null;

        // Act
        boolean isValid = clientHandler.isValidInput(nullInput);

        // Assert
        assertFalse(isValid, "Expected null input to return false");
    }

}