package com.blog.controller;

import com.alibaba.fastjson.JSON;
import com.blog.base.BaseController;
import com.blog.base.BaseService;
import com.blog.base.Response;
import com.blog.domain.Article;
import com.blog.domain.Comment;
import com.blog.domain.Content;
import com.blog.enums.ArticleEnum;
import com.blog.enums.FileEnum;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;
import com.blog.service.ContentService;
import com.blog.service.UserService;
import com.blog.utils.FileUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.omg.PortableInterceptor.ACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章
 * author:xiujiang.liu
 * Date:2018/12/12
 * Time:23:48
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController<Article> {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    ArticleService articleService;

    FileUtils fileUtils;

    @Autowired
    CommentService commentService;

    @Autowired
    ArticleController(ArticleService articleService) {
        super(articleService);
        this.articleService = articleService;
    }

    @Autowired
    UserService userService;

    @Autowired
    ContentService contentService;

    @PostMapping("/addArticle")
    @ResponseBody
    public Response addArticle(@RequestBody Article article) {
        logger.info("article:{}", article);
//        logger.info("authorId:{},title:{},content:{}",authorId,title,content);

//        if(authorId == 0 || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)){
//            return new Response("10","文章信息为空",null);
//        }
//        if(!userService.isExist(authorId)){
//            return new Response("10","用户信息不存在",null);
//        }

        Content newContent = new Content(article.getContent());
        newContent = contentService.add(newContent);
//        Article article = new Article(title,authorId,categoryId,newContent.getId(),description,0, ArticleEnum.ACTIVE);
        article.setContentId(newContent.getId());
        article.setStatus(ArticleEnum.ACTIVE);
        articleService.add(article);
        return new Response().success(article);
    }

    @PostMapping("/updateArticle")
    @ResponseBody
    public Response updateArticle(@RequestBody Article article) {
        if(article==null){
            return new Response().success(null);
        }
        Article oldArticle = articleService.get(article.getId());
        if (ObjectUtils.isEmpty(oldArticle)) {
            return new Response().success(null);
        }
        if (!article.getAuthorId().equals(article.getAuthorId())) {
            return new Response().error("不存在该帖子", null);
        }
        oldArticle.setDescription(article.getDescription());
        oldArticle.setTitle(article.getTitle());
        oldArticle.setCategoryId(article.getCategoryId());
        if (!StringUtils.isEmpty(article.getContent())) {
            contentService.update(oldArticle.getContentId(), article.getContent());
        }
        this.articleService.update(oldArticle);
        return new Response().success(oldArticle);
    }

    /**
     * 根据分类信息查询文章
     *
     * @param pageNum
     * @param categoryId
     * @return
     */
    @GetMapping("/findAllByCategory")
    public Response findAllByCategory(int pageNum, int categoryId, int authorId) {
        Page<Article> articles = articleService.findAll(pageNum, 20, new Article(null, null, categoryId, null, null, null, null));
        return new Response().success(articles);
    }


    /**
     * 分页查询文章
     *
     * @param pageNum
     * @return
     */
    @GetMapping("/findAllArticle")
    @ResponseBody
    public Response findAllArticle(int pageNum, int categoryId) {
        Article article = new Article();
        if (categoryId != 0) {
            article.setCategoryId(categoryId);
        }
        Page<Article> articlePage = this.articleService.findAll(pageNum, 10, article);
        logger.info("articlePage" + articlePage.getContent());
        return new Response().success(articlePage);
    }

    /**
     * 获取单条文章
     *
     * @param articleId
     * @return
     */
    @GetMapping("/articleInfo")
    @ResponseBody
    public Response getContentById(int articleId) {
        logger.info("id:{}", articleId);
        Article article = this.articleService.get(articleId);
        if (ObjectUtils.isEmpty(article)) {
            return new Response("10", "文章不存在", null);
        }
        logger.info("article:{}", article);
        Content content = this.contentService.get(article.getContentId());
        logger.info("content:{}", content);
        if (!ObjectUtils.isEmpty(content)) {
            article.setContent(content.getContent());
        }
        List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        article.setComments(comments);
        return new Response().success(article);
    }

    @PostMapping("/article/upload")
    @ResponseBody
    public Map uploadImg(@RequestParam MultipartFile file) throws IOException, FileUploadException {
        checkFile(file);
        Map maps = new HashMap();
        try {
            FileUtils.upload(file.getInputStream(), FileEnum.PICTURE, null, null);
            maps.put("errno", 0);
            maps.put("data", new String[]{"data:image/jpeg;base64,"+ new String(Base64Utils.encode(file.getBytes()),"utf-8")});
        } catch (Exception e) {
            maps.put("errno", 1);
            maps.put("data","");
        }
        return maps;
    }

    private void checkFile(MultipartFile file) {
        if (file.getSize() == 0) {
            return;
        }
    }
}
