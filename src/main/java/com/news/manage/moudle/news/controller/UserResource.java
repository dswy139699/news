package com.news.manage.moudle.news.controller;

import com.alibaba.fastjson.JSON;
import com.news.manage.moudle.news.Config.jwt.CheckToken;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.domain.UserVO;
import com.news.manage.moudle.news.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class UserResource {
    Logger logger = Logger.getLogger(UserResource.class);

    @Resource
    private UserService userService;

//    @CheckToken
    @PostMapping(value = "/manage")
    public ResponseModel<UserVO> userManage(@RequestBody UserVO userVO){
        logger.info("user manage input: " + JSON.toJSONString(userVO));
        return userService.createOrUpdateUser(userVO);
    }

    @CheckToken
    @PostMapping(value = "/query")
    public ResponseModel<List<UserVO>> queryUser(@RequestBody UserVO userVO){
        logger.info("user query input: " + JSON.toJSONString(userVO));
        ResponseModel<List<UserVO>> listResponseModel = userService.queryUserVOList(userVO);
        logger.info("user query response: " + JSON.toJSONString(listResponseModel));
        return listResponseModel;
    }
}
