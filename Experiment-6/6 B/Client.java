import java.io.*;
import java.net.*;
class Client {
 public static void main(String args[]) {
 try {
 // BufferedReader for user input
 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 // Create a socket to connect to the server
 Socket clsct = new Socket("127.0.0.1", 3000);
 // BufferedReader to read from the server's input stream
 BufferedReader din = new BufferedReader(new
InputStreamReader(clsct.getInputStream()));
 // DataOutputStream to write to the server's output stream
 DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
 // Prompt user to enter the MAC address
 System.out.println("Enter the Physical Address (MAC):");
 String str1 = in.readLine(); // Read user input
 // Send the MAC address to the server
 dout.writeBytes(str1 + '\n');
 // Read the server's response
 String str = din.readLine();
 System.out.println("The Logical address is (IP): " + str);
 // Close the connection
 clsct.close();
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}