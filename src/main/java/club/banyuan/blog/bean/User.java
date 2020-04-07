package club.banyuan.blog.bean;

import lombok.Data;

@Data
public class User {
    Integer id;
    String name;
    String password;
    String email;
    String avatar;
}
