package club.banyuan.blog;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.BlogDao;
import club.banyuan.blog.dao.CommentDao;
import club.banyuan.blog.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
@MapperScan("club.banyuan.blog")
public class BlogApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(BlogApplication.class, args);
		UserDao userDao = (UserDao) context.getBean(UserDao.class);
		User user1 = userDao.findUserByName("aa");
		System.out.println(user1.toString());
		BlogDao blogDao = (BlogDao) context.getBean(BlogDao.class);
		Blog blog = blogDao.getBlogDetail(12);
		System.out.println(blog);
		/*
		CommentDao commentDao = (CommentDao) context.getBean(CommentDao.class);
		List<Comment> comments = commentDao.findCommentsByBlogId(1);
		System.out.println(comments);
		 */

	}

}
