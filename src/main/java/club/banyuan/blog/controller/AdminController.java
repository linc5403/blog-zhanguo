package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BlogService blogService;

    @GetMapping("")
    String showAdminPage(@RequestParam Optional<Integer> page,
                         @RequestParam Optional<Integer> size,
                         HttpSession session,
                         Model model) {
        User user = (User)session.getAttribute("USER");
        model.addAttribute("username", user.getName());
        PageHelper.startPage(page.orElse(1), size.orElse(10));
        model.addAttribute("blogs",
                new PageInfo<Blog>(blogService.showAuthorBlogs(user.getName())));
        return "admin";
    }
}
