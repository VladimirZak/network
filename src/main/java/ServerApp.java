import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) throws IOException {

        int port = 8081;

        while (true) {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.printf("New connection accepted port: %d%n", clientSocket.getPort());
            out.println("Enter your name. If you want to exit, type \"exit\".");
            String name = "";
            boolean flagQuestionName = false, flagQuestionAge = false;
            start:
            while (true) {
                String str = in.readLine();
                if (str.equals("exit")) {
                    break;
                }

                if (!str.isEmpty() && !flagQuestionName && !str.equals(" ")) {
                    out.println(String.format("Hi, %s. Are you 18 already? Type Y or N", str));
                    name = str;
                    flagQuestionName = true;
                    continue start;
                }

                if (str.equalsIgnoreCase("y") && !flagQuestionAge) {
                    out.println(String.format("Excellent! %s are you ready to starting? Type Y or N", name));
                    flagQuestionAge = true;
                    continue start;

                }

                if (str.equalsIgnoreCase("n") && !flagQuestionAge) {
                    out.println("You are still young. Go to bed.");
                    break;
                }

                if (str.equalsIgnoreCase("y")) {
                    out.println(String.format("Now, it's show time!!! Dear, %s!", name));
                    break;
                }

                if (str.equalsIgnoreCase("n")) {
                    out.println("So sorry. Bye bye.");
                    break;
                }

                if (str.equals(" ") || str.isEmpty() || !str.equalsIgnoreCase("y") || !str.equalsIgnoreCase("n")) {
                    out.println("Try again");
                    continue start;
                }
            }
            out.println("exit");
            serverSocket.close();
        }
    }
}
