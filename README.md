# Socket-Server
## Overview
The Poem Client-Server project is a simple Java application that demonstrates client-server communication using sockets. The server responds to client requests by providing lines from a poem stored in a text file. The client can send requests(number) to the server to retrieve specific lines or nthline from the poem.

## Features
  - Client-server communication over TCP/IP sockets.
  - Server responds to client requests by providing lines from a poem.
  - Continuous communication between the client and server until the client sends a termination signal.

## Requirements
  - Java Development Kit (JDK) 8 or higher
  - Intellij IDEA Community Edition 2023 or later (or any other Java IDE).
  - Basic understanding of Java programming.

## Installation
  1. Clone the repository to your local machine:
    `git clone https://github.com/caidoc/ExerciseProject1`
  2. Open the project in Intellij IDEA.
  3. Compile the Java files.

## Usage
### Server
  1. Run the **Server** class to start the server.
     `Server.java`
  2. The server will start listening on the specified port.

## Configuration
  - Modify the **config.properties** file if necessary to change the **filePath** and **port**.

## Reference
  - [A Guide to Java Sockets](https://www.baeldung.com/a-guide-to-java-sockets)
  - [Read an InputStream using the Java Server Socket](https://www.baeldung.com/java-inputstream-server-socket)
  - [How to Read a File in Java](https://www.baeldung.com/reading-file-in-java)
  - [Read and Write User Input in Java](https://www.baeldung.com/java-console-input-output)
     
