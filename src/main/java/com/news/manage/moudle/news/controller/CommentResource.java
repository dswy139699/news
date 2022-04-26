package com.news.manage.moudle.news.controller;


import com.news.manage.moudle.news.Config.jwt.CheckToken;
import com.news.manage.moudle.news.domain.CommentVO;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.service.NewsService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentResource {

    @Resource
    private NewsService newsService;

    @CheckToken
    @PostMapping(value = "/manage")
    public ResponseModel<CommentVO> commentManage(@RequestBody CommentVO commentVO){
        CommentVO commentVO1 = newsService.manageComment(commentVO);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), commentVO1);
    }

    @CheckToken
    @PostMapping(value = "/queryList")
    public ResponseModel<List<CommentVO>> queryList(@RequestBody CommentVO commentVO){
        ResponseModel<List<CommentVO>> commentList = newsService.queryCommentVOList(commentVO);
        return commentList;
    }
}
