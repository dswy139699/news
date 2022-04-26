package com.news.manage.moudle.news.controller;


import com.alibaba.fastjson.JSON;
import com.news.manage.moudle.news.Config.jwt.CheckToken;
import com.news.manage.moudle.news.domain.NewsVO;
import com.news.manage.moudle.news.domain.QueryModel;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/service")
public class ManageResource {
    Logger logger = Logger.getLogger(ManageResource.class);

    @Resource
    private NewsService newsService;

    @CheckToken
    @PostMapping("/manage")
    public ResponseModel manageNews(@RequestBody NewsVO newsVO){
        logger.info("manage news input: " + JSON.toJSONString(newsVO));
        newsService.manageNews(newsVO);
        return new ResponseModel(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), "");
    }

    @CheckToken
    @PostMapping(value = "/query")
    public ResponseModel<List<NewsVO>> queryNews(@RequestBody QueryModel queryModel){
        logger.info("query news input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<NewsVO>> responseModel = newsService.queryNewsVOList(queryModel);
        logger.info("query news response: " +JSON.toJSONString(responseModel));
        return responseModel;
    }

}
