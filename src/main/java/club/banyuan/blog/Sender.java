package club.banyuan.blog;
import java.util.concurrent.TimeUnit;

import club.banyuan.blog.config.RabbitConfig;
import club.banyuan.blog.service.Receiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    // private final Receiver receiver;

    // public Sender(Receiver receiver, RabbitTemplate rabbitTemplate) {
    public Sender(RabbitTemplate rabbitTemplate) {
        // this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        //rabbitTemplate.convertAndSend(rabbitTemplate.getExchange(), "foo.bar.baz", "Hello from RabbitMQ!");
        rabbitTemplate.convertAndSend(RabbitConfig.getTopicExchangeName(), "foo.bar.baz", "Hello from RabbitMQ!");
        //rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", "Hello from RabbitMQ!");
        //receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
