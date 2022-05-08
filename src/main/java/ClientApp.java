import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) {

        String host = "netology.homework";
        int port = 8081;
        Scanner scan = new Scanner(System.in);

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            while (true) {
                String reply = in.readLine();
                if (reply.equals("exit")) {
                    break;
                } else {
                    System.out.println(reply);
                    String str = scan.nextLine();
                    out.println(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
