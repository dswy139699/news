package com.news.manage.moudle.news.domain;

public class TokenModel {
    private String token;
    private UserVO userVO;

    public TokenModel(String token, UserVO userVO){
        this.token = token;
        this.userVO = userVO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}
