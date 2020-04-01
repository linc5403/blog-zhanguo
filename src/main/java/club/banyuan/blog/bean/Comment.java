package club.banyuan.blog.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    Integer id;
    Date createdTime;
    String content;
    User commenter;
}
