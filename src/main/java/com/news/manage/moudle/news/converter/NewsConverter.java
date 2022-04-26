package com.news.manage.moudle.news.converter;

import com.news.manage.moudle.news.domain.*;
import com.news.manage.moudle.news.repo.dao.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.SimpleFormatter;

@Mapper(componentModel = "spring")
public interface NewsConverter {

    NewsEntity toNewEntity(NewsVO newsVO);
    NewsVO toNewsVO(NewsEntity newsEntity);

    List<NewsVO> toNewsVOList(List<NewsEntity> newsEntityList);

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    default String date2String(Date date){
        if(Objects.isNull(date))
            return null;
        return df.format(date);
    }

    default Date string2Date(String str) throws ParseException {
        if(str == null)
            return null;
        return df.parse(str);
    }


    /**
     * 人员转换
     */
    UserVO toUserVO(UserEntity userEntity);
    UserEntity toUserEntity(UserVO userVO);
    List<UserVO> toUserVOList(List<UserEntity> userEntityList);

    /**
     * tab转换
     */
    TabVO toTabVO(TabEntity tabEntity);
    TabEntity toTabEntity(TabVO tabVO);
    List<TabVO> toTabVOList(List<TabEntity> tabEntityList);

    /**
     * comment转换
     */
    CommentVO toCommentVO(CommentEntity commentEntity);
    CommentEntity toCommentEntity(CommentVO commentVO);
    List<CommentVO> toCommentVOList(List<CommentEntity> commentEntityList);


    /**
     * file转换
     * @param fileEntity
     * @return
     */
    FileVO toFileVO(FileEntity fileEntity);
    FileEntity toFileEntity(FileVO fileVO);
    List<FileVO> toFileVOList(List<FileEntity> fileEntityList);
}
