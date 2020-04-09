package club.banyuan.blog;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.BlogDao;
import club.banyuan.blog.dao.CommentDao;
import club.banyuan.blog.dao.UserDao;
import club.banyuan.blog.service.MailService;
import club.banyuan.blog.service.RedisService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.mail.MessagingException;
import java.util.List;

@SpringBootApplication
@MapperScan("club.banyuan.blog")
public class BlogApplication {

	public static void main(String[] args) throws MessagingException {
		ApplicationContext context =  SpringApplication.run(BlogApplication.class, args);
		/*
		UserDao userDao = (UserDao) context.getBean(UserDao.class);
		User user1 = userDao.findUserByName("aa");
		System.out.println(user1.toString());
		BlogDao blogDao = (BlogDao) context.getBean(BlogDao.class);
		Blog blog = blogDao.getBlogDetail(12);
		System.out.println(blog);
		List<Blog> blogs = blogDao.findBlogsByUsername("aa");
		System.out.println(blogs);
		 */

		/*
		MailService mailService = (MailService) context.getBean(MailService.class);
		mailService.sendActiveMessage("aa");
		RedisService redisService = (RedisService) context.getBean(RedisService.class);
		redisService.testRedis();
		 */
	}

}
