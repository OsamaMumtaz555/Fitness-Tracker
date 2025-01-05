import java.io.*;
import java.net.*;
import java.util.HashMap;

public class FAQServer {
    private static final int PORT = 5000; // Server port
    private static HashMap<String, String> faqMap;

    public FAQServer() {
        initializeFAQ(); // Initialize the FAQs
    }

    // Start the FAQ Server
    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("FAQ Server is running on port " + PORT + "...");

            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle the client in a separate thread
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // Initialize FAQs
    private void initializeFAQ() {
        faqMap = new HashMap<>();
        faqMap.put("What is the Gym Management System?", "The Gym Management System is a software application designed to manage memberships, track attendance, schedule classes, and handle billing and payments for gyms and fitness centers.");
        faqMap.put("How can I register for a membership?", "Log in to the system, browse membership plans, and select the plan that suits your needs.");
        faqMap.put("What payment methods are accepted?", "We accept credit cards, debit cards, and PayPal.");
        faqMap.put("Can I cancel or refund my membership?", "Membership cancellations and refunds are available within 7 days of purchase, as per the gym's policy. Please contact support for assistance.");
        faqMap.put("How do I contact support?", "You can contact support at support@gymmanagement.com");
    }

    // ClientHandler to manage each client's connection
    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String clientQuery;
                while ((clientQuery = in.readLine()) != null) {
                    System.out.println("Client asked: " + clientQuery);
                    String response = faqMap.getOrDefault(clientQuery, "Sorry, I don't have an answer for that.");
                    out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Client connection error: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
    }
}
