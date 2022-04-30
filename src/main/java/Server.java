import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8089;
        while (true){
            try (ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
                 Socket clientSocket = serverSocket.accept(); // ждем подключения
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                System.out.println("New connection accepted");

                out.println("Write your name");
                String name = in.readLine();
                out.println("Are you child? (yes/no)");
                String isChild = in.readLine();
                if (isChild.equalsIgnoreCase("yes")) {
                    out.println("Welcome to the kids area, " + name + ". Let's play!");
                } else if (isChild.equalsIgnoreCase("no")) {
                    out.println("Welcome to the adult zone, " + name + ". Have a good rest, or a good working day!");
                } else {
                    out.println("Error! Try again");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
