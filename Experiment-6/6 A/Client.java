import java.io.*;
import java.net.*;
class Client {
 public static void main(String args[]) {
 try {
 // BufferedReader for user input
 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
 // Connect to the server on localhost at port 3636
 Socket clsct = new Socket("127.0.0.1", 3636);
 // BufferedReader to read from server input stream
 BufferedReader din = new BufferedReader(new
InputStreamReader(clsct.getInputStream()));
 // DataOutputStream to write to server output stream
 DataOutputStream dout = new DataOutputStream(clsct.getOutputStream());
 System.out.println("Enter the Logical address (IP):");
 String str1 = in.readLine(); // Read user input
 dout.writeBytes(str1 + '\n'); // Send IP to server
 // Read the response from the server
 String str = din.readLine();
 System.out.println("The Physical Address is: " + str);
 // Close the connection
 clsct.close();
 } catch (Exception e) {
 System.out.println(e);
 }
 }
}