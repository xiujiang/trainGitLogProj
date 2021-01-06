package com.blog.exception;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/7/18
 * @since 1.8
 */
public class ServiceException extends RuntimeException {

    private String code = "";
    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
