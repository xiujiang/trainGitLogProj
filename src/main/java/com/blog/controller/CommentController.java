package com.blog.controller;

import com.blog.base.BaseController;
import com.blog.base.BaseService;
import com.blog.base.Response;
import com.blog.domain.Comment;
import com.blog.domain.Content;
import com.blog.exception.ServiceException;
import com.blog.service.CommentService;
import com.blog.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:49
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController<Comment> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContentService contentService;

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        super(commentService);
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    @ResponseBody
    public Response addComment(@RequestBody Comment comment){
        logger.info("comment:{}", comment);
        try{
            checkComment(comment);
        }catch (Exception e){
            return new Response().error(e,null);
        }
        Content newContent = new Content(comment.getContent());
        newContent = contentService.add(newContent);
        comment.setContentId(newContent.getId());
        commentService.add(comment);
        return new Response().success(null);
    }

    private String checkComment(Comment comment){
        if(ObjectUtils.isEmpty(comment) || ObjectUtils.isEmpty(comment.getContent()) || ObjectUtils.isEmpty(comment.getArticleId())){
            throw new ServiceException("10", "评论信息为空");
        }
        return null;
    }


    @GetMapping("/commentPage")
    @ResponseBody
    public Response<Page<Comment>> getCommentPageByArticleId(Integer pageNum,Integer articleId){
        if(articleId == null){
            return new Response<>().success(null);
        }
        Page<Comment> comments = commentService.getCommentsPageByArticleId(pageNum,articleId);
        return new Response<>().success(comments);
    }
}
