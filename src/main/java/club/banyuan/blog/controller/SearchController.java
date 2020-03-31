package club.banyuan.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    // 按照关键词搜索文章标题
    @ResponseBody
    @GetMapping("/search")
    String searchTitle(@RequestParam(value = "key") String keyword) {
        // 调用service进行查找，返回list
        return "You are searching " + keyword;
    }
}
