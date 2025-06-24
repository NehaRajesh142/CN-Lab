import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost port 6666
            Socket socket = new Socket("localhost", 6666);
            System.out.println("Connected to server.");

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String message = "";
            while (!message.equalsIgnoreCase("stop")) {
                System.out.print("Enter message to server: ");
                message = scanner.nextLine();

                dout.writeUTF(message);   // send message to server
                dout.flush();

                if (message.equalsIgnoreCase("stop")) {
                    break;
                }

                String reply = din.readUTF();  // read server echo
                System.out.println("Server echoed: " + reply);
            }

            // Close all
            din.close();
            dout.close();
            socket.close();
            scanner.close();
            System.out.println("Client stopped.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
