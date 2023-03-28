import java.io.*;
import java.net.*;

public class RandomNumberServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        int port = 2348;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }

        System.out.println("Waiting for client connection...");

        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                try {
                    int n = Integer.parseInt(inputLine);
                    int[] randomNumbers = generateRandomNumbers(n);
                    out.println(arrayToString(randomNumbers));
                } catch (NumberFormatException e) {
                    out.println("Invalid input. Please enter a positive integer.");
                }
            }

            out.close();
            in.close();
            clientSocket.close();
        }
    }

    private static int[] generateRandomNumbers(int n) {
        int maxNumber = (int) (Math.random() * n);
        int[] randomNumbers = new int[maxNumber + 1];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = (int) (Math.random() * n) + 1;
        }
        return randomNumbers;
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
