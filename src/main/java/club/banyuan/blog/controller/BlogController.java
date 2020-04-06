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


}
