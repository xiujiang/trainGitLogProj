package com.blog.domain;

import com.blog.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:41
 */
@Entity
@Table(name = "comment")
@Data
public class Comment extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "reply_author_id")
    private Integer replyAuthorId;

    @Column(name = "content_id")
    private Integer contentId;

    @Transient
    private String content;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    public Comment(Integer id, Integer articleId, Integer replyAuthorId, Integer contentId, String content) {
        this.id = id;
        this.articleId = articleId;
        this.replyAuthorId = replyAuthorId;
        this.contentId = contentId;
        this.content = content;
    }

    public Comment() {
    }
}
