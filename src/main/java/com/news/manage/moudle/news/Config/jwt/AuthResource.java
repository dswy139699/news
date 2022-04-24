package com.news.manage.moudle.news.Config.jwt;


import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.domain.TokenModel;
import com.news.manage.moudle.news.domain.UserVO;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseModel<TokenModel> userLogin(HttpServletRequest request) {
        String userId = request.getHeader("userId");
        String password = request.getHeader("passWord");
        UserVO userVO = userService.checkUserPWD(userId, password);
        if (Objects.nonNull(userVO)) {
            String sign = JwtUtil.sign(userId, password);
            return new ResponseModel<TokenModel>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), new TokenModel(sign, userVO));
        }
        return new ResponseModel<TokenModel>(ErrorEnum.FAIL.getCode(), ErrorEnum.FAIL.getMsg(), new TokenModel("", null));
    }


}

