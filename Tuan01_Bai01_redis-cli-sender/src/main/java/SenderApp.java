import redis.clients.jedis.Jedis;

import java.util.Scanner;

public class SenderApp {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Redis CLI Sender ===");

        while (true) {
            System.out.print("Enter message (or 'exit'): ");
            String message = scanner.nextLine();

            if ("exit".equalsIgnoreCase(message)) {
                break;
            }

            jedis.lpush("message_queue", message);
            System.out.println("Sent: " + message);
        }

        jedis.close();
        System.out.println("Sender stopped.");
    }
}
