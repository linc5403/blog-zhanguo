package club.banyuan.blog.service;

import club.banyuan.blog.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Object obj) {
        rabbitTemplate.convertAndSend(RabbitConfig.getTopicExchangeName(), "foo.bar.baz", obj);
    }
}
