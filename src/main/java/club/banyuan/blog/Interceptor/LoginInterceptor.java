package club.banyuan.blog.Interceptor;

import club.banyuan.blog.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("USER");
        if (user == null) {
            response.sendRedirect("/login?next=".concat(request.getRequestURI()));
            return false;
        }
        return true;
    }
}
