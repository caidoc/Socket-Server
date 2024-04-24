package com._98labs.exercises.sockets;

import java.io.IOException;

public interface PoemReader {
    String getLine(int lineNumber) throws IOException;
}
