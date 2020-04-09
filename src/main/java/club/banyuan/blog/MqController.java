package club.banyuan.blog;

import club.banyuan.blog.service.MqSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MqController {
    @Autowired
    private MqSender mqSender;

    @GetMapping("/message")
    @ResponseBody
    String sendMessage(@RequestParam String msg) {
        mqSender.sendMessage(msg);
        return "send success.";
    }
}
