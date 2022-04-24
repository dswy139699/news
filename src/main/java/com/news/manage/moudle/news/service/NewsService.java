package com.news.manage.moudle.news.service;


import com.news.manage.moudle.news.converter.NewsConverter;
import com.news.manage.moudle.news.domain.*;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.repo.dao.NewsEntity;
import com.news.manage.moudle.news.repo.dao.TabEntity;
import com.news.manage.moudle.news.repo.mapper.NewsRepository;
import com.news.manage.moudle.news.repo.mapper.TabRepository;
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

    private NewsConverter newsConverter;
    private NewsRepository newsRepository;
    private TabRepository tabRepository;
    private UserService userService;

    NewsService(NewsConverter newsConverter,
                NewsRepository newsRepository,
                TabRepository tabRepository,
                UserService userService){
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
        this.tabRepository = tabRepository;
        this.userService = userService;
    }

    /**
     * 新闻创建更新
     * @param newsVO
     */
    public void manageNews(NewsVO newsVO){
        UserVO userVO = userService.queryUserByToken();
        newsVO.setAuthorId(userVO.getUserId());
        newsVO.setAuthorName(userVO.getName());
        NewsEntity newsEntity = newsConverter.toNewEntity(newsVO);
        newsRepository.save(newsEntity);
    }

    /**
     * 栏目创建更新
     * @param tabVO
     */
    public void manageTab(TabVO tabVO){
        UserVO userVO = userService.queryUserByToken();
        tabVO.setAuthorId(userVO.getUserId());
        tabVO.setAuthorName(userVO.getName());
        TabEntity tabEntity = newsConverter.toTabEntity(tabVO);
        tabRepository.save(tabEntity);
    }


    /**
     * 根据条件查询新闻信息
     * @param queryModel
     * @return
     */
    public List<NewsEntity> queryNewsList(QueryModel queryModel){
        Specification<NewsEntity> specification = (Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            if(Objects.nonNull(queryModel.getAuthorId())){
                Predicate predicate = cb.equal(root.get(AUTHOR_ID), queryModel.getAuthorId());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getAuthorName())){
                Predicate predicate = cb.equal(root.get(AUTHOR_NAME), queryModel.getAuthorName());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getTabId())){
                Predicate predicate = cb.equal(root.get(TAB_ID), queryModel.getTabId());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getTitle())){
                Predicate predicate = cb.equal(root.get(TITLE), queryModel.getTitle());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getTabName())){
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
            if(Objects.nonNull(queryModel.getTabId())){
                Predicate predicate = cb.equal(root.get(UUID), queryModel.getTabId());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getTabName())){
                Predicate predicate = cb.like(root.get(TAB_NAME), queryModel.getTabName());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getAuthorId())){
                Predicate predicate = cb.equal(root.get(AUTHOR_ID), queryModel.getAuthorId());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getAuthorName())){
                Predicate predicate = cb.like(root.get(AUTHOR_NAME), queryModel.getAuthorName());
                pList.add(predicate);
            }
            if(Objects.nonNull(queryModel.getDescription())){
                Predicate predicate = cb.like(root.get(DESCRIPTION), queryModel.getDescription());
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

}
