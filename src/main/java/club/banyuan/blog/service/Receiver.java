package club.banyuan.blog.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.CountDownLatch;

// @Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        // 收到消息之后做的动作
        // message可以是string，还可以是任意的序列化的Java对象
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public void xxx() {
        //
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
