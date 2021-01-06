package com.blog.service;

import com.blog.base.BaseDao;
import com.blog.base.BaseService;
import com.blog.dao.ContentDao;
import com.blog.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * author:xiujiang.liu
 * Date:2018/12/16
 * Time:21:45
 */
@Service
public class ContentService extends BaseService<Content> {

    ContentDao contentDao;

    @Autowired
    public ContentService(ContentDao contentDao) {
        super(contentDao);
        this.contentDao = contentDao;
    }

    public void update(int id,String content){
        Optional<Content> oldContent = this.contentDao.findById(id);
        if(oldContent.isPresent()){
            Content content1 = oldContent.get();
            content1.setContent(content);
            this.update(content1);
        }
    }
}
