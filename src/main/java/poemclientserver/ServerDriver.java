package poemclientserver;

public class ServerDriver {
    public static void main(String[] args){
        final String filePath = "C:\\Users\\Lenovo\\IdeaProjects\\ExerciseProject1\\src\\main\\resources\\Still I Rise.txt";
        final int port = 12345;

        PoemServer server = new PoemServer(filePath, port);
        server.start();
    }
}
