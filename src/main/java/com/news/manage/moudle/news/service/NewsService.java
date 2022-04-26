package com.news.manage.moudle.news.service;


import com.news.manage.moudle.news.controller.CommentResource;
import com.news.manage.moudle.news.converter.NewsConverter;
import com.news.manage.moudle.news.domain.*;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.repo.dao.CommentEntity;
import com.news.manage.moudle.news.repo.dao.NewsEntity;
import com.news.manage.moudle.news.repo.dao.TabEntity;
import com.news.manage.moudle.news.repo.mapper.CommentRepository;
import com.news.manage.moudle.news.repo.mapper.NewsRepository;
import com.news.manage.moudle.news.repo.mapper.TabRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class NewsService {

    private static String UUID = "uuid";
    private static String TAB_ID = "tabId";
    private static String TAB_NAME = "tabName";
    private static String AUTHOR_ID = "authorId";
    private static String AUTHOR_NAME = "authorName";
    private static String TITLE = "title";
    private static String CONTENT = "content";
    private static String STATUS_ENUM = "statusEnum";
    private static String DESCRIPTION = "description";

    private static String NEW_ID = "newsId";
    private static String LINKED_COMMENT_ID="linkedCommentId";
    private static String COMMENT_BODY="commentBody";


    private NewsConverter newsConverter;
    private NewsRepository newsRepository;
    private TabRepository tabRepository;
    private CommentRepository commentRepository;
    private UserService userService;

    NewsService(NewsConverter newsConverter,
                NewsRepository newsRepository,
                TabRepository tabRepository,
                CommentRepository commentRepository,
                UserService userService){
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
        this.tabRepository = tabRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    /**
     * 新闻创建更新
     * @param newsVO
     */
    public void manageNews(NewsVO newsVO){
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            newsVO.setAuthorId(userVO.getUserId());
            newsVO.setAuthorName(userVO.getName());
        }
        NewsEntity newsEntity = newsConverter.toNewEntity(newsVO);
        newsRepository.save(newsEntity);
    }

    /**
     * 栏目创建更新
     * @param tabVO
     */
    public void manageTab(TabVO tabVO){
        if(Objects.isNull(tabVO.getTabName()))
            return;
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            tabVO.setAuthorId(userVO.getUserId());
            tabVO.setAuthorName(userVO.getName());
        }
        TabEntity tabEntity = newsConverter.toTabEntity(tabVO);
        tabRepository.save(tabEntity);
    }

    /**
     * 评论创建更新
     * @param commentVO
     */
    public void manageComment(CommentVO commentVO){
        if(Objects.isNull(commentVO.getCommentBody()))
            return;
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            commentVO.setAuthorId(userVO.getUserId());
            commentVO.setAuthorName(userVO.getName());
        }
        CommentEntity commentEntity = newsConverter.toCommentEntity(commentVO);
        commentRepository.save(commentEntity);
    }


    /**
     * 根据条件查询新闻信息
     * @param queryModel
     * @return
     */
    public List<NewsEntity> queryNewsList(QueryModel queryModel){
        Specification<NewsEntity> specification = (Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            if(StringUtils.isNotEmpty(queryModel.getAuthorId())){
                Predicate predicate = cb.equal(root.get(AUTHOR_ID), queryModel.getAuthorId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getAuthorName())){
                Predicate predicate = cb.equal(root.get(AUTHOR_NAME), queryModel.getAuthorName());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getTabId())){
                Predicate predicate = cb.equal(root.get(TAB_ID), queryModel.getTabId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getTitle())){
                Predicate predicate = cb.equal(root.get(TITLE), queryModel.getTitle());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getTabName())){
                Predicate predicate = cb.equal(root.get(TAB_NAME), queryModel.getTabName());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getStatusEnum())){
                Predicate predicate = cb.equal(root.get(STATUS_ENUM), queryModel.getStatusEnum());
                pList.add(predicate);
            }
            Predicate[] pArray = new Predicate[pList.size()];
            query.where(pList.toArray(pArray));
            return null;
        };
        return newsRepository.findAll(specification);
    }

    public ResponseModel<List<NewsVO>> queryNewsVOList(QueryModel queryModel){
        List<NewsEntity> newsEntities = this.queryNewsList(queryModel);
        List<NewsVO> newsVOS = newsConverter.toNewsVOList(newsEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsVOS);
    }

    /**
     * 根据条件查询tab
     * @param queryModel
     * @return
     */
    public List<TabEntity> queryTabList(QueryModel queryModel){
        Specification<TabEntity> specification = (Root<TabEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            Predicate predicate1 = cb.isNotNull(root.get(UUID));
            pList.add(predicate1);
            if(StringUtils.isNotEmpty(queryModel.getTabId())){
                Predicate predicate = cb.equal(root.get(UUID), queryModel.getTabId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getTabName())){
                Predicate predicate = cb.like(root.get(TAB_NAME), queryModel.getTabName());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getAuthorId())){
                Predicate predicate = cb.equal(root.get(AUTHOR_ID), queryModel.getAuthorId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getAuthorName())){
                Predicate predicate = cb.like(root.get(AUTHOR_NAME), queryModel.getAuthorName());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(queryModel.getDescription())){
                Predicate predicate = cb.like(root.get(DESCRIPTION), queryModel.getDescription());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getStatusEnum())){
                Predicate predicate = cb.equal(root.get(STATUS_ENUM), queryModel.getStatusEnum());
                pList.add(predicate);
            }
            Predicate[] pArray = new Predicate[pList.size()];
            query.where(pList.toArray(pArray));
            return null;
        };
        return tabRepository.findAll(specification);
    }

    public ResponseModel<List<TabVO>> queryTabVOList(QueryModel queryModel){
        List<TabEntity> tabEntities = this.queryTabList(queryModel);
        List<TabVO> tabVOS = newsConverter.toTabVOList(tabEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), tabVOS);
    }



    public List<CommentEntity> queryCommentList(CommentVO commentVO){
        Specification<CommentEntity> specification = (Root<CommentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            Predicate predicate1 = cb.isNotNull(root.get(UUID));
            pList.add(predicate1);
            if(StringUtils.isNotEmpty(commentVO.getNewsId())){
                Predicate predicate = cb.equal(root.get(NEW_ID), commentVO.getNewsId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(commentVO.getAuthorId())){
                Predicate predicate = cb.like(root.get(AUTHOR_ID), commentVO.getAuthorId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(commentVO.getLinkedCommentId())){
                Predicate predicate = cb.equal(root.get(LINKED_COMMENT_ID), commentVO.getLinkedCommentId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(commentVO.getAuthorName())){
                Predicate predicate = cb.like(root.get(AUTHOR_NAME), commentVO.getAuthorName());
                pList.add(predicate);
            }
            if(Objects.nonNull(commentVO.getStatusEnum())){
                Predicate predicate = cb.equal(root.get(STATUS_ENUM), commentVO.getStatusEnum());
                pList.add(predicate);
            }
            Predicate[] pArray = new Predicate[pList.size()];
            query.where(pList.toArray(pArray));
            return null;
        };
        return commentRepository.findAll(specification);
    }

    public ResponseModel<List<CommentVO>> queryCommentVOList(CommentVO commentVO){
        List<CommentEntity> commentEntities = this.queryCommentList(commentVO);
        List<CommentVO> commentVOS = newsConverter.toCommentVOList(commentEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), commentVOS);
    }

}
