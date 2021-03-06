package com.news.manage.moudle.news.Config.jwt;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class JwtConfig extends WebSecurityConfigurerAdapter{
//
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception{
////        return super.authenticationManagerBean();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        //????????????????????????????????????
//        web.ignoring().antMatchers("/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http)throws Exception{
//        http.authorizeRequests()
//                .anyRequest().authenticated();
//    }
//
//}
//

@Configuration
public class JwtConfig implements WebMvcConfigurer {
    /**
     * ??????jwt?????????????????????????????????
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .excludePathPatterns("/auth/login");
    }

    /**
     * jwt?????????
     * */
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.????????????converters?????????????????????
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2.??????fastjson????????????????????????: ??????????????????????????????json??????
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 3.???converter?????????????????????
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //????????????????????????
        List<MediaType> oFastMediaTypeList = new ArrayList<>();
        oFastMediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(oFastMediaTypeList);
        // 4.???converter?????????HttpMessageConverter
        HttpMessageConverter<?> converter = fastConverter;
        // 5.??????HttpMessageConverters??????
        return new HttpMessageConverters(converter);
    }

}
