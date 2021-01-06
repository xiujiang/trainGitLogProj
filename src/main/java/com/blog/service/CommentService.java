package com.blog.service;

import com.blog.base.BaseService;
import com.blog.dao.CommentDao;
import com.blog.domain.Comment;
import com.blog.domain.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:48
 */
@Service
public class CommentService extends BaseService<Comment> {

    @Autowired
    ContentService contentService;

    CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        super(commentDao);
        this.commentDao = commentDao;
    }

    public List<Comment> getCommentsByArticleId(Integer articleId){
        Example<Comment> commentExample = Example.of(new Comment(null,articleId,null,null,null));
        List<Comment> comments = this.commentDao.findAll(commentExample,Sort.by(Sort.Direction.DESC,"createTime"));
        comments.forEach(o->{
            if(!ObjectUtils.isEmpty(o.getContentId())){
                Content content = contentService.get(o.getContentId());
                if(!ObjectUtils.isEmpty(content)){
                    o.setContent(content.getContent());
                }
            }
        });
        return comments;
    }


    public Page<Comment> getCommentsPageByArticleId(Integer pageNum,Integer articleId){
        Example<Comment> commentExample = Example.of(new Comment(null,articleId,null,null,null));
        Pageable pageable = PageRequest.of(pageNum,10,Sort.by(Sort.Direction.DESC,"createTime"));
        Page<Comment> comments = this.commentDao.findAll(commentExample,pageable);
        if(ObjectUtils.isEmpty(comments.getContent())){
            return comments;
        }
        comments.getContent().forEach(o->{
            if(!ObjectUtils.isEmpty(o.getContentId())){
                Content content = contentService.get(o.getContentId());
                if(!ObjectUtils.isEmpty(content)){
                    o.setContent(content.getContent());
                }
            }
        });
        return comments;
    }
}
