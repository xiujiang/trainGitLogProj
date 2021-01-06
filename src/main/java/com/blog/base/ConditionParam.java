package com.blog.base;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import lombok.Data;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/22
 * @since 1.8
 */

@Data
public class ConditionParam {
    private Object value;
    private String field;
    private String operation;

    public ConditionParam(Object value, String field, String operation) {
        this.value = value;
        this.field = field;
        this.operation = operation;
    }
}
