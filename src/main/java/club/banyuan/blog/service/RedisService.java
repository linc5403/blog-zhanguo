package club.banyuan.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void testRedis() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("haha", "abc", 60, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("haha"));
    }
}
