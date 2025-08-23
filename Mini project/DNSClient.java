import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);

            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                System.out.print("Enter hostname (or 'exit' to quit): ");
                String hostname = sc.nextLine();
                if (hostname.equalsIgnoreCase("exit")) break;

                sendBuffer = hostname.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, 6060);
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Response from server: " + response);
            }

            clientSocket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
