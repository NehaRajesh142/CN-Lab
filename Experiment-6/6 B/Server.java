import java.io.*;
import java.net.*;
class Server {
 public static void main(String args[]) {
 try {
 // Create a server socket on port 3000
 ServerSocket obj = new ServerSocket(3000);
 System.out.println("Server started, waiting for client...");
 // Accept the incoming client connection
 Socket obj1 = obj.accept();
 System.out.println("Client connected.");
 // BufferedReader to read from client input stream
 BufferedReader din = new BufferedReader(new
InputStreamReader(obj1.getInputStream()));
 // DataOutputStream to write to client output stream
 DataOutputStream dout = new DataOutputStream(obj1.getOutputStream());
 // Define IP and MAC address pairs
 String[] ip = {"165.165.80.80", "165.165.79.1"};
 String[] mac = {"6A:08:AA:C2", "8A:BC:E3:FA"};
 while (true) {
 // Read the MAC address from the client
 String str = din.readLine();
 if (str == null) {
 break; // Exit the loop if input is null (e.g., client disconnects)
 }
 boolean found = false;
 // Look for the corresponding IP address for the given MAC
 for (int i = 0; i < mac.length; i++) {
 if (str.equals(mac[i])) {
 dout.writeBytes(ip[i] + '\n');
 found = true;
 break;
 }
 }
 if (!found) {
 dout.writeBytes("IP not found\n");
 }
 }
 // Close the connection
 obj1.close();
 obj.close();
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}
