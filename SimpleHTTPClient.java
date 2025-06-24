import java.io.*;
import java.net.*;

public class SimpleHTTPClient {
    public static void main(String[] args) {
        try {
            // Connect to a different host (example.org on port 80)
            Socket socket = new Socket("example.org", 80);

            // Prepare the output stream to send HTTP request
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send HTTP GET request for the root page
            out.println("GET / HTTP/1.1");
            out.println("Host: example.org");
            out.println("Connection: close");  // Ensure server closes the connection after response
            out.println();  // Blank line to indicate end of request headers

            // Read the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // Close connections
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
