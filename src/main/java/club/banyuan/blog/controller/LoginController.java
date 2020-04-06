package club.banyuan.blog.controller;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    String showLogin(HttpSession session,
                     @RequestParam(value = "next", required = false) String next) {
        if (next != null)
            session.setAttribute("NEXT", next);
        return "login";
    }

    @PostMapping("/login")
    String login(@RequestParam(value = "username") String name,
                 @RequestParam(value = "password") String passwd,
                 HttpSession session) {
        User user = userService.findUserByName(name);
        if (user.getPassword().equals(passwd)) {
            session.setAttribute("USER", user);
            String next = (String)session.getAttribute("NEXT");
            if (next != null) {
                session.removeAttribute("NEXT");
                return "redirect:" + next;
            }
            else
                return "redirect:/admin";
        } else {
            return "/login";
        }
    }
}
