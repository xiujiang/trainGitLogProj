package com.blog.domain;

import com.blog.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:37
 */
@Entity
@Table(name = "category")
@Data
public class Category extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "level")
    private int level;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

}
