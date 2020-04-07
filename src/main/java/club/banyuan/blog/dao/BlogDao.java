package club.banyuan.blog.dao;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    Blog findBlogById(Integer id);
    List<Blog> findBlogsByUsername(String username);
    List<Blog> getAllBlogs();
    // 获取blog及其comments
    Blog getBlogDetail(Integer id);
    void insertBlog(Blog blog);
    void updateBlog(Blog blog);
    void deleteBlog(Integer id);
}
