package poemclientserver;

public class Main {

    public static void main(String[] args){
        final String serverAddress = "localhost";
        final int port = 12345;
        final String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\java\\poemclientserver\\Still I Rise.txt";

        PoemServer server = new PoemServer(filePath, port);
        PoemClient client = new PoemClient(serverAddress, port);

        Thread serverThread = new Thread(server::start);
        serverThread.start();

        client.sendMessage(1);

    }
}
