package com.blog.controller;
import java.util.Map;
import	java.util.logging.Level;

import com.blog.base.BaseController;
import com.blog.base.BaseService;
import com.blog.base.Response;
import com.blog.domain.Category;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 类别
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:50
 */
@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<Category> {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }


    @PostMapping("/getAll")
    public Response getAllCategory(){
        return new Response().success(this.categoryService.findAll());
    }

    @PostMapping("/getAllByLevel")
    public Response getAllCategoryByLevel(Integer level,Integer parent){
        return new Response().success(this.categoryService.getCategoryByLevel(level,parent));
    }


}
