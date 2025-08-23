import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class DNSServer {
    public static void main(String[] args) {
        try {
            HashMap<String, String> dnsTable = new HashMap<>();
            dnsTable.put("google.com", "142.250.195.78");
            dnsTable.put("openai.com", "104.16.99.52");
            dnsTable.put("example.com", "93.184.216.34");

            DatagramSocket serverSocket = new DatagramSocket(6060);
            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            System.out.println("DNS Server started on port 5050...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String hostname = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();

                System.out.println("Received query: " + hostname);

                String response = dnsTable.getOrDefault(hostname, "Host not found");

                sendBuffer = response.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
