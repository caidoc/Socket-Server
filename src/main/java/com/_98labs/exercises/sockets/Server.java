package com._98labs.exercises.sockets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Server {
   // private static final Logger logger = LogManager.getLogger(Server.class);
    public static void main(String[] args){
        final String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt";
        final int port = 12345;

        PoemServer server = new PoemServer(filePath, port);
        server.start();
    }
}
