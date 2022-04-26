package com.news.manage.moudle.news.controller;


import com.alibaba.fastjson.JSON;
import com.news.manage.moudle.news.Config.jwt.CheckToken;
import com.news.manage.moudle.news.domain.FileVO;
import com.news.manage.moudle.news.domain.NewsVO;
import com.news.manage.moudle.news.domain.QueryModel;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        NewsVO newsVO1 = newsService.manageNews(newsVO);
        return new ResponseModel(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsVO1);
    }

    @CheckToken
    @PostMapping(value = "/query")
    public ResponseModel<List<NewsVO>> queryNews(@RequestBody QueryModel queryModel){
        logger.info("query news input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<NewsVO>> responseModel = newsService.queryNewsVOList(queryModel);
        logger.info("query news response: " +JSON.toJSONString(responseModel));
        return responseModel;
    }

    @CheckToken
    @PostMapping(value = "/queryHot")
    public ResponseModel<List<NewsVO>> queryHotNews(){
//        logger.info("query news input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<NewsVO>> responseModel = newsService.queryHotNews();
        logger.info("query hot news response: " +JSON.toJSONString(responseModel));
        return responseModel;
    }

    @CheckToken
    @PostMapping(value = "/queryNew")
    public ResponseModel<List<NewsVO>> queryNewNews(){
//        logger.info("query news input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<NewsVO>> responseModel = newsService.queryHotNews();
        logger.info("query new news response: " +JSON.toJSONString(responseModel));
        return responseModel;
    }


    @CheckToken
    @PostMapping(value = "/queryRecommend")
    public ResponseModel<List<NewsVO>> queryRecommendNews(){
//        logger.info("query news input: " + JSON.toJSONString(queryModel));
        ResponseModel<List<NewsVO>> responseModel = newsService.queryRecommendNews();
        logger.info("query recommend news response: " +JSON.toJSONString(responseModel));
        return responseModel;
    }

    @CheckToken
    @PostMapping(value = "/uploadFile")
    public ResponseModel  uploadFile(@RequestParam("file") MultipartFile file){
        ResponseModel<FileVO> fileVOResponseModel = newsService.uploadFile(file);
        return fileVOResponseModel;
    }

}
