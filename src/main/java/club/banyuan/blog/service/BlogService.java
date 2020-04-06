package club.banyuan.blog.service;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    private BlogDao blogDao;


    public Blog getDetailById(Integer id) {
        return blogDao.getBlogDetail(id);
    }

    public List<Blog> showAuthorBlogs(String name) {
        return blogDao.findBlogsByUsername(name);
    }

    public Integer addBlog(Blog blog) {
        blogDao.insertBlog(blog);
        return blog.getId();
    }

    public PageInfo<Blog> showAllBlogs(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return new PageInfo<Blog>(blogDao.getAllBlogs());
    }

    public void updateBlog(Blog blog) {
        blogDao.updateBlog(blog);
    }

    public void deleteBlog(Integer blogId) {
        blogDao.deleteBlog(blogId);
    }
}
