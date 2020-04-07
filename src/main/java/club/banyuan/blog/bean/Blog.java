package club.banyuan.blog.bean;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Blog {
    Integer id;
    String title;
    String content;
    Date createdTime;
    // Integer userId;
    User author;
    List<Comment> comments;
}
