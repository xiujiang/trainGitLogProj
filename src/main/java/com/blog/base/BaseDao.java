package com.blog.base;/*
 * Copyright 2017 - 数多科技
 *
 * 北京数多信息科技有限公司 本公司保留所有下述内容的权利。
 * 本内容为保密信息，仅限本公司内部使用。
 * 非经本公司书面许可，任何人不得外泄或用于其他目的。
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2018/12/13
 * @since 1.8
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T,Integer> {

}
