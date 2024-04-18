# Poem Client-Server
## Overview
The Poem Client-Server project is a simple Java application that demonstrates client-server communication using sockets. The server responds to client requests by providing lines from a poem stored in a text file. The client can send requests(number) to the server to retrieve specific lines or nthline from the poem.

## Features
  - Client-server communication over TCP/IP sockets.
  - Server responds to client requests by providing lines from a poem.
  - Client can send requests to the server to retrieve specific lines from the poem.
  - Continuous communication between the client and server until the client sends a termination signal.

## Requirements
  - Java Development Kit (JDK) installed on your system.
  - Intellij IDEA Community Edition 2023 or later (or any other Java IDE).
  - Basic understanding of Java programming.

## Installation
  1. Clone the repository to your local machine:
    `git clone https://github.com/caidoc/ExerciseProject1`
  2. Open the project in Intellij IDEA.
  3. Compile the Java files.

## Usage
### Server
  1. Run the **ServerDriver** class to start the server.
     `java ServerDriver`
  2. The server will start listening on the specified port.

### Client
  1. Run the **ClientDriver** class to start the client.
     `java ClientDriver`
  2. Enter a line number to request a specific line from the poem. Enter **00** to terminate the connection.

## Configuration
  - Modify the **filePath** to change the poem that the server responds with.
  - Update the server **port** in the PoemServer class if needed.

## Acknowledgements
  - A Guide to Java Sockets
  - Read an InputStream using the Java Server Socket
  - How to Read a File in Java
  - Read and Write User Input in Java
     
