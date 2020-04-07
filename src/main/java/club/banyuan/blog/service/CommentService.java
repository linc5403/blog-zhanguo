package club.banyuan.blog.service;

import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public List<Comment> getCommentsByBlogId(Integer id) {
        return commentDao.findCommentsByBlogId(id);
    }
}
