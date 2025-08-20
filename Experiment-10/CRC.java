import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dataword
        System.out.print("Enter the dataword (in binary): ");
        String data = sc.nextLine();

        // Input generator polynomial
        System.out.print("Enter the generator polynomial (in binary): ");
        String generator = sc.nextLine();

        int m = data.length();
        int n = generator.length();

        // Append n-1 zeros to data
        String dividend = data;
        for (int i = 0; i < n - 1; i++) {
            dividend += "0";
        }

        // Perform modulo-2 division
        String remainder = divide(dividend, generator);

        // Codeword = data + remainder
        String codeword = data + remainder;
        System.out.println("Generated Codeword (to be transmitted): " + codeword);

        // Receiver side
        System.out.print("Enter the received codeword: ");
        String received = sc.nextLine();

        String recRemainder = divide(received, generator);

        if (Integer.parseInt(recRemainder, 2) == 0) {
            System.out.println("No Error Detected ✅");
        } else {
            System.out.println("Error Detected ❌");
        }

        sc.close();
    }

    // Function to perform modulo-2 division
    static String divide(String dividend, String divisor) {
        char[] remainder = dividend.toCharArray();
        int n = divisor.length();

        for (int i = 0; i <= remainder.length - n; i++) {
            if (remainder[i] == '1') {
                for (int j = 0; j < n; j++) {
                    remainder[i + j] = (remainder[i + j] == divisor.charAt(j)) ? '0' : '1';
                }
            }
        }

        // Extract last (n-1) bits as remainder
        return new String(remainder).substring(remainder.length - (n - 1));
    }
}
