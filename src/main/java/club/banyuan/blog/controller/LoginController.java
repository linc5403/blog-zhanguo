package club.banyuan.blog.controller;

import club.banyuan.blog.bean.User;
import club.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

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

    @PostMapping("/login/change-password")
    String changePassword(HttpSession session,
                          @RequestParam(value = "oldPasswd") String oldPassword,
                          @RequestParam(value = "newPasswd") String newPassword,
                          Model model
                          ) {
        String username = ((User)session.getAttribute("USER")).getName();
        User user = userService.findUserByName(username);
        String oldInDB = user.getPassword();
        if (oldInDB.equals(oldPassword)) {
            //old password 正确
            user.setPassword(newPassword);
            userService.updatePasswd(newPassword, user.getId());
            model.addAttribute("message", "修改密码成功");
            model.addAttribute("user", user);
            return "/admin";
        } else {
            // 原始密码不正确,怎么处理??
            model.addAttribute("message", "原始密码不正确");
            model.addAttribute("user", user);
            return "/admin";
        }
    }

    @PostMapping("/logout")
    String logout(HttpSession session) {
        session.removeAttribute("USER");
        return "redirect:/";
    }
}
