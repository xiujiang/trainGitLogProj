package com.blog.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * author:xiujiang.liu
 * Date:2018/12/13
 * Time:23:42
 */
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public abstract class BaseDomain {
    private Integer id;

    private LocalDateTime createTime;

    private LocalDateTime lastUpdateTime;

}
