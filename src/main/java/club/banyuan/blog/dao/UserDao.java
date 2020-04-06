package club.banyuan.blog.dao;

import org.springframework.stereotype.Repository;
import club.banyuan.blog.bean.User;

@Repository
public interface UserDao {
    User findUserByName(String username);
    void updatePasswd(String passwd, Integer id);
}
