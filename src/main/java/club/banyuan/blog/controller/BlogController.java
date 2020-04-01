package club.banyuan.blog.controller;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.BlogService;
import club.banyuan.blog.service.CommentService;
import club.banyuan.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;

    // 按用户名访问用户的博客
    @GetMapping("/blogger/{username}")
    String showBlogsByUserName(@PathVariable(value = "username") String userName,
                               @RequestParam Optional<Integer> page,
                               @RequestParam Optional<Integer> size,
                               Model model) {
        // username -> user's blogs
        // blogs -> view (list.html)
        // return "list"
        PageHelper.startPage(page.orElse(1), size.orElse(10), "id asc");
        List<Blog> blogs = blogService.showAuthorBlogs(userName);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        User user = blogs.get(0).getAuthor();
        model.addAttribute("blogs", pageInfo);
        model.addAttribute("user", user);
        return "list";
    }

    // 按照blogId查询blog的信息
    // /blog/{id}
    @GetMapping("/blog/{id}")
    String showBlogById(@PathVariable(value = "id")  Integer id, Model model) {
        Blog blog = blogService.getDetailById(id);
        model.addAttribute("blog", blog);
        // 根据blogId查找这个blog对应的评论，放到comments对象中
        model.addAttribute("comments", blog.getComments());
        return "item";
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
}
