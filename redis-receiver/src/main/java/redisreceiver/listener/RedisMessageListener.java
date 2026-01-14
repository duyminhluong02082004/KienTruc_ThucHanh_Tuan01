package redisreceiver.listener;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisMessageListener {

    @PostConstruct
    public void startListening() {

        new Thread(() -> {
            Jedis jedis = new Jedis("localhost", 6379);
            System.out.println("=== Redis Receiver Started ===");

            while (true) {
                // brpop: chờ message, không tốn CPU
                String message = jedis.brpop(0, "message_queue").get(1);
                System.out.println("Received: " + message);
            }

        }).start();
    }
}
