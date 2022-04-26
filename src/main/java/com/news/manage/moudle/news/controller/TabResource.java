package com.news.manage.moudle.news.controller;


import com.alibaba.fastjson.JSON;
import com.news.manage.moudle.news.Config.jwt.CheckToken;
import com.news.manage.moudle.news.domain.QueryModel;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.domain.TabVO;
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
@RequestMapping("/tab")
public class TabResource {

    Logger logger = Logger.getLogger(TabResource.class);

    @Resource
    private NewsService newsService;


    @CheckToken
    @PostMapping(value = "/manage")
    public ResponseModel tabManage(@RequestBody TabVO tabVO){
        logger.info("manage tab input: " + JSON.toJSONString(tabVO));
        newsService.manageTab(tabVO);
        return new ResponseModel(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), "");
    }


    @CheckToken
    @PostMapping(value = "/queryList")
    public ResponseModel<List<TabVO>> queryTabList(@RequestBody QueryModel queryModel){
        logger.info("query tab input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<TabVO>> listResponseModel = newsService.queryTabVOList(queryModel);
        logger.info("query tab response: " + JSON.toJSONString(listResponseModel));
        return listResponseModel;
    }
}
