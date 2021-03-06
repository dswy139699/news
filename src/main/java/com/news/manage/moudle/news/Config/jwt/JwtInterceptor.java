package com.news.manage.moudle.news.Config.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws HttpStatusCodeException {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断是否需要进行校验token
        if (method.isAnnotationPresent(CheckToken.class)) {
            CheckToken checkToken = method.getAnnotation(CheckToken.class);
            if (checkToken.required()) {

                //从 http 请求头中取出 token
                String token = request.getHeader("token");
//                System.out.println("此处测试是否拿到了token：" + token);

                try {
                    if (token == null) {

                        response.setStatus(HttpStatus.UNAUTHORIZED.value());
                        return false;
                    }
                    JwtUtil.checkSign(token);
                } catch (Exception e){
//                    throw  new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                }

                Authentication authentication = null;
                if(Objects.nonNull(token) && !"".equals(token)){
                    authentication = generateAuthentication(token);
                }
                if(Objects.nonNull(authentication)){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                return true;
            }
        }
        return true;
    }

    private Authentication generateAuthentication(String token){
        String userId = JwtUtil.getUserId(token);
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("admin"));
        return new UsernamePasswordAuthenticationToken(userId, token, grantedAuthorityList);
    }
}
