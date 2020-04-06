package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @GetMapping("")
    String showAdminPage(HttpSession session, Model model) {
        User user = (User)session.getAttribute("USER");
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/blogs")
    String showAdminBlogsPage(@RequestParam Optional<Integer> page,
                         @RequestParam Optional<Integer> size,
                         HttpSession session,
                         Model model) {
        User user = (User)session.getAttribute("USER");
        model.addAttribute("username", user.getName());
        PageHelper.startPage(page.orElse(1), size.orElse(10));
        model.addAttribute("blogs",
                new PageInfo<Blog>(blogService.showAuthorBlogs(user.getName())));
        return "admin-blogs";
    }

    String showAdminAccount() {
        return "admin-account";
    }

    // 展示创建blog的页面
    @GetMapping("/blog/create")
    String showCreatePage(HttpSession session) {
        if (session.getAttribute("USER") != null) {
            return "create";
        }
        else {
            return "redirect:/login";
        }
    }

    // 用户提交blog
    @PostMapping("/blog/create")
    String createBlog(@RequestParam(value = "title") String title,
                      @RequestParam(value = "content") String content) {
        //????blogger?????
        User user_aa = userService.findUserByName("aa");
        Blog blog = new Blog();
        blog.setAuthor(user_aa);
        blog.setTitle(title);
        blog.setContent(content);
        // 将blog增加进数据库
        Integer blogId = blogService.addBlog(blog);
        // 展示所创建的blog ?? item.html
        return "redirect:/blog/" + blogId;
    }


    @GetMapping("/blog/{id}/edit")
    String showBlogEditPage(@PathVariable(value = "id") Integer blogId,
                            Model model) {
        Blog blog = blogService.getDetailById(blogId);
        model.addAttribute("blog", blog);
        return "edit";
    }


    @PutMapping("/blog/{id}/edit")
    String updateBlog(@PathVariable(value = "id") Integer blogId,
                      @RequestParam(value = "title") String title,
                      @RequestParam(value = "content") String content
                      ) {
        Blog blog = new Blog();
        blog.setContent(content);
        blog.setTitle(title);
        blog.setId(blogId);
        blogService.updateBlog(blog);
        return "redirect:/blog/"+blogId;
    }

    @DeleteMapping("/blog/{id}")
    String deleteBlog(@PathVariable(value = "id") Integer blogId
    ) {
        blogService.deleteBlog(blogId);
        return "redirect:/admin/blogs";
    }
}
