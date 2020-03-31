package club.banyuan.blog.service;

import club.banyuan.blog.bean.Blog;
import club.banyuan.blog.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    @Autowired
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    private BlogDao blogDao;

    public Blog getDetailById(Integer id) {
        return blogDao.findBlogById(id);
    }
}
