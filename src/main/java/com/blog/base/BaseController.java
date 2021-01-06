package com.blog.base;

import com.blog.service.ArticleService;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * author:xiujiang.liu
 * Date:2018/12/11
 * Time:23:50
 */
public abstract class BaseController<T> {

    private BaseService<T> service;

    public BaseController(BaseService<T> baseService){
        this.service = baseService;
    }

    @PostMapping("/add")
    public T add(T data){
        data = service.add(data);
        return data;
    }

}
