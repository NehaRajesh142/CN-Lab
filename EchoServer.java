import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        DataInputStream dataInput = null;
        DataOutputStream dataOutput = null;

        try {
            serverSocket = new ServerSocket(6666);
            System.out.println("EchoServer is running and waiting for a client...");

            clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            dataInput = new DataInputStream(clientSocket.getInputStream());
            dataOutput = new DataOutputStream(clientSocket.getOutputStream());

            String message = "";

            while (!message.equalsIgnoreCase("stop")) {
                message = dataInput.readUTF();
                System.out.println("Received: " + message);

                dataOutput.writeUTF("Echo: " + message);
                dataOutput.flush();
            }

            System.out.println("Stopping server...");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (dataInput != null) dataInput.close();
                if (dataOutput != null) dataOutput.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
