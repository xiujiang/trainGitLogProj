package com.blog.service;

import com.blog.base.BaseService;
import com.blog.dao.CategoryDao;
import com.blog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:45
 */
@Service
public class CategoryService extends BaseService<Category> {

    CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        super(categoryDao);
        this.categoryDao = categoryDao;
    }


    public List<Category> getCategoryByLevel(Integer level,Integer parent){
        Category category = new Category();
        category.setLevel(level);
        category.setParent(parent);
        Example<Category> categoryExample = Example.of(category);
        return this.categoryDao.findAll(categoryExample);

    }
}
