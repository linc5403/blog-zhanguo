package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    // 按用户名访问用户的博客
    @GetMapping("/blogger/{username}")
    @ResponseBody
    String showBlogsByUserName(@PathVariable(value = "username") String userName) {
        // username -> user's blogs
        // blogs -> view (list.html)
        // return "list"
        return "You are request " + userName + " blogs";
    }

    // 按照blogId查询blog的信息
    // /blog/{id}
    @GetMapping("/blog/{id}")
    String showBlogById(@PathVariable(value = "id")  Integer id, Model model) {
        Blog blog = blogService.getDetailById(id);
        model.addAttribute("blog", blog);
        return "item";
    }
}
