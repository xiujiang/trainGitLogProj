package com.blog.domain;

import com.blog.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author:xiujiang.liu
 * Date:2018/12/11
 * Time:23:38
 */
@Entity
@Table(name = "user")
@Data
public class User extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name="create_time")
    private LocalDateTime createTime;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @Column(name = "remark")
    private String remark;

    public User() {
    }

    public User(String name, String password, String email, LocalDateTime createTime, LocalDateTime lastUpdateTime, String remark) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.remark = remark;
    }
}
