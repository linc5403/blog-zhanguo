package club.banyuan.blog.service;

import club.banyuan.blog.bean.Comment;
import club.banyuan.blog.bean.User;
import club.banyuan.blog.dao.CommentDao;
import club.banyuan.blog.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }
    public void updatePasswd(String newPasswd, Integer id) {
        userDao.updatePasswd(newPasswd, id);
    }
    public void updateAvatarById(Integer id, String avatarPath) {
        userDao.updateAvatar(id, avatarPath);
    };

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }
}
