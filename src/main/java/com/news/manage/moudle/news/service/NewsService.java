package com.news.manage.moudle.news.service;


import com.news.manage.moudle.news.constant.NewsConstant;
import com.news.manage.moudle.news.converter.NewsConverter;
import com.news.manage.moudle.news.domain.*;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.enums.StatusEnum;
import com.news.manage.moudle.news.repo.dao.CommentEntity;
import com.news.manage.moudle.news.repo.dao.FileEntity;
import com.news.manage.moudle.news.repo.dao.NewsEntity;
import com.news.manage.moudle.news.repo.dao.TabEntity;
import com.news.manage.moudle.news.repo.mapper.CommentRepository;
import com.news.manage.moudle.news.repo.mapper.FIleRepository;
import com.news.manage.moudle.news.repo.mapper.NewsRepository;
import com.news.manage.moudle.news.repo.mapper.TabRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.File;
import java.io.IOException;
import java.util.*;


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
    private FIleRepository fIleRepository;
    private UserService userService;

    NewsService(NewsConverter newsConverter,
                NewsRepository newsRepository,
                TabRepository tabRepository,
                CommentRepository commentRepository,
                FIleRepository fIleRepository,
                UserService userService){
        this.newsConverter = newsConverter;
        this.newsRepository = newsRepository;
        this.tabRepository = tabRepository;
        this.commentRepository = commentRepository;
        this.fIleRepository = fIleRepository;
        this.userService = userService;
    }

    /**
     * 新闻创建更新
     * @param newsVO
     */
    public NewsVO manageNews(NewsVO newsVO){
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            newsVO.setAuthorId(userVO.getUserId());
            newsVO.setAuthorName(userVO.getName());
        }
        NewsEntity newsEntity = null;
        if(StringUtils.isNotEmpty(newsVO.getUuid()) && StatusEnum.DELETED.equals(newsVO.getStatusEnum())){
            newsEntity = newsRepository.findByUuid(newsVO.getUuid());
            if(Objects.isNull(newsEntity)){
                newsEntity = newsConverter.toNewEntity(newsVO);
            }
            newsEntity.setStatusEnum(StatusEnum.DELETED);
        } else {
            newsEntity = newsConverter.toNewEntity(newsVO);
        }
        if(StringUtils.isNotEmpty(newsEntity.getTabId())){
            TabEntity tabEntity = Optional.ofNullable(tabRepository.findByUuid(newsEntity.getTabId())).orElse(new TabEntity());
            newsEntity.setTabName(tabEntity.getTabName());
        }
        newsRepository.save(newsEntity);
        return newsConverter.toNewsVO(newsEntity);
    }

    /**
     * 栏目创建更新
     * @param tabVO
     */
    public TabVO manageTab(TabVO tabVO){
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            tabVO.setAuthorId(userVO.getUserId());
            tabVO.setAuthorName(userVO.getName());
        }
        TabEntity tabEntity = null;
        if(StringUtils.isNotEmpty(tabVO.getUuid()) && StatusEnum.DELETED.equals(tabVO.getStatusEnum())){
            tabEntity = tabRepository.findByUuid(tabVO.getUuid());
            if(Objects.isNull(tabEntity)){
                tabEntity = newsConverter.toTabEntity(tabVO);
            }
            tabEntity.setStatusEnum(StatusEnum.DELETED);
        } else {
            tabEntity = newsConverter.toTabEntity(tabVO);
        }
        tabRepository.save(tabEntity);
        return newsConverter.toTabVO(tabEntity);
    }

    /**
     * 评论创建更新
     * @param commentVO
     */
    public CommentVO manageComment(CommentVO commentVO){
        UserVO userVO = userService.queryUserByToken();
        if(Objects.nonNull(userVO)) {
            commentVO.setAuthorId(userVO.getUserId());
            commentVO.setAuthorName(userVO.getName());
        }
        if(StringUtils.isNotEmpty(commentVO.getLinkedCommentId())){
            CommentEntity commentEntity = Optional.ofNullable(commentRepository.findByUuid(commentVO.getLinkedCommentId())).orElse(new CommentEntity());
            commentVO.setLinkedAuthorId(commentEntity.getAuthorId());
            commentVO.setLinkedAuthorName(commentEntity.getAuthorName());
        }
        CommentEntity commentEntity = null;
        if(StringUtils.isNotEmpty(commentVO.getUuid()) && StatusEnum.DELETED.equals(commentVO.getStatusEnum())){
            commentEntity = commentRepository.findByUuid(commentVO.getUuid());
            if(Objects.isNull(commentEntity)){
                commentEntity = newsConverter.toCommentEntity(commentVO);
            }
            commentEntity.setStatusEnum(StatusEnum.DELETED);
        } else {
            commentEntity = newsConverter.toCommentEntity(commentVO);
        }
        commentRepository.save(commentEntity);
        return newsConverter.toCommentVO(commentEntity);
    }


    /**
     * 根据条件查询新闻信息
     * @param queryModel
     * @return
     */
    public List<NewsEntity> queryNewsList(QueryModel queryModel){
        Specification<NewsEntity> specification = (Root<NewsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            if(StringUtils.isNotEmpty(queryModel.getUuid())){
                Predicate predicate = cb.equal(root.get(UUID), queryModel.getUuid());
                pList.add(predicate);
            }
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
        if(StringUtils.isNotEmpty(queryModel.getUuid()) && !CollectionUtils.isEmpty(newsEntities)){
            NewsEntity entity = newsEntities.get(0);
            entity.setClickCount(entity.getClickCount() == null? 1 : entity.getClickCount()+1);
            newsRepository.save(entity);
        }
        List<NewsVO> newsVOS = newsConverter.toNewsVOList(newsEntities);
        newsVOS.forEach(t -> {
            if (StringUtils.isEmpty(t.getTabName())){
                QueryModel queryModelTab = new QueryModel();
                queryModelTab.setTabId(t.getTabId());
                List<TabEntity> tabVOS = queryTabList(queryModelTab);
                if (!CollectionUtils.isEmpty(tabVOS)) {
                    t.setTabName(tabVOS.get(0).getTabName());
                }
            }
        });
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsVOS);
    }


    /**
     * 查询热点新闻
     * @return
     */
    public ResponseModel<List<NewsVO>> queryHotNews(){
        Pageable pageable = PageRequest.of(0, 10);
        List<NewsEntity> newsEntities = newsRepository.findAllByOrderByClickCountDesc(pageable);
        List<NewsVO> newsVOS = newsConverter.toNewsVOList(newsEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsVOS);
    }

    /**
     * 查询最新新闻
     * @return
     */
    public ResponseModel<List<NewsVO>> queryNewNews(){
        Pageable pageable = PageRequest.of(0, 10);
        List<NewsEntity> newsEntities = newsRepository.findAllByOrderByCreateTimeDesc(pageable);
        List<NewsVO> newsVOS = newsConverter.toNewsVOList(newsEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsVOS);
    }

    /**
     * 查询推荐新闻
     * @return
     */
    public ResponseModel<List<NewsVO>> queryRecommendNews(){
        List<NewsEntity> newsEntities = newsRepository.findAllByRandom(10);
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
            if(StringUtils.isNotEmpty(commentVO.getTabId())){
                Predicate predicate = cb.equal(root.get(TAB_ID), commentVO.getTabId());
                pList.add(predicate);
            }
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
        if(!CollectionUtils.isEmpty(commentVOS)){
            commentVOS.forEach(t -> {
                TabEntity tabEntity = Optional.ofNullable(tabRepository.findByUuid(t.getTabId())).orElse(new TabEntity());
                NewsEntity newsEntity = Optional.ofNullable(newsRepository.findByUuid(t.getNewsId())).orElse(new NewsEntity());
                t.setTabName(tabEntity.getTabName());
                t.setTitle(newsEntity.getTitle());
            });
        }
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), commentVOS);
    }

    /**
     * 文件存储
     * @param file
     * @return
     */
    public ResponseModel<FileVO> uploadFile(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        String fileType = file.getContentType();
        String currentName = java.util.UUID.randomUUID().toString();
        String currentFilePath = NewsConstant.IP_ADDRESS + NewsConstant.FILE_LOAD + currentName + fileType;
        FileEntity fileEntity = new FileEntity(fileName, fileType, currentName, currentFilePath);
        File localFile = new File(NewsConstant.FILE_PATH + currentName + fileType);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            fIleRepository.save(fileEntity);
            return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), newsConverter.toFileVO(fileEntity));
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseModel<>(ErrorEnum.FILE_SAVE_FAILED.getCode(), ErrorEnum.FILE_SAVE_FAILED.getMsg(), null);
        }
    }
}
