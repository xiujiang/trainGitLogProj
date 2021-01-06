package com.blog.enums;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import com.blog.base.BaseEnum;
import com.blog.convert.EnumConvert;
import com.blog.domain.Article;

import javax.persistence.Convert;

/**
 * 文章枚举
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2018/12/18
 * @since 1.8
 */
public enum  ArticleEnum implements BaseEnum<ArticleEnum> {

    /**
     * 文章已生效
     */
    ACTIVE(0,1,"ACTIVE","生效"),
    /**
     * 文章已删除
     */
    DELETE(0,2,"DELETE","删除");

    private Integer id;
    private Integer value;
    private String code;
    private String name;

    ArticleEnum(Integer id, Integer value, String code, String name) {
        this.id = id;
        this.value = value;
        this.code = code;
        this.name = name;
    }
    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getCode() {
        return this.code;
    }


    public static ArticleEnum valueOf(Integer value) {
        return BaseEnum.valueOf(ArticleEnum.class, value);
    }

    public static ArticleEnum fromString(String str) {
        return BaseEnum.fromString(ArticleEnum.class, str);
    }


    @Convert
    public static class AsCode extends EnumConvert<ArticleEnum> {
        public AsCode() {
            super(ArticleEnum.class);
        }
    }
}
