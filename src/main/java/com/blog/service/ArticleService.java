package com.blog.service;

import com.blog.base.BaseService;
import com.blog.dao.ArticleDao;
import com.blog.domain.Article;
import com.blog.domain.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:47
 */
@Service
public class ArticleService extends BaseService<Article> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    ArticleDao articleDao;

    @Autowired
    ContentService contentService;

    @Autowired
    public ArticleService(ArticleDao articleDao){
        super(articleDao);
        this.articleDao = articleDao;
    }



    public Page<Article> findAll(int pageNum,int size,Article article){
        Pageable pageable = PageRequest.of(pageNum,size,Sort.by(Sort.Direction.DESC,"createTime"));
        Example<Article> articleExample = Example.of(article);
        Page<Article> pageArticle = this.articleDao.findAll(articleExample,pageable);
        pageArticle.stream().forEach(a->{
            if(ObjectUtils.isEmpty(a.getContentId())){
                return;
            }
            Content content = contentService.get(a.getContentId());
            if(!ObjectUtils.isEmpty(content)){
                a.setContent(content.getContent());
            }
        });
        return pageArticle;
    }

}
