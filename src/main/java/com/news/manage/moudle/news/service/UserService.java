package com.news.manage.moudle.news.service;

import com.news.manage.moudle.news.Config.jwt.SecurityUtil;
import com.news.manage.moudle.news.converter.NewsConverter;
import com.news.manage.moudle.news.domain.ResponseModel;
import com.news.manage.moudle.news.domain.UserVO;
import com.news.manage.moudle.news.enums.ErrorEnum;
import com.news.manage.moudle.news.repo.dao.UserEntity;
import com.news.manage.moudle.news.repo.mapper.UserRepository;
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
import java.util.Optional;

@Service
public class UserService {

    private static String USER_ID = "userId";
    private static String USER_NAME = "userName";
    private static String UUID = "uuid";

    private UserRepository userRepository;

    private NewsConverter newsConverter;

    public UserService(UserRepository userRepository,
                       NewsConverter newsConverter){
        this.userRepository = userRepository;
        this.newsConverter = newsConverter;
    }

    public UserVO queryUserByToken(){
        String userId = SecurityUtil.getUserId();
        if(Objects.nonNull(userId)){
            return queryUserById(userId);
        }
        return null;
    }

    /**
     * 根据用户名查询人员信息
     * @param userId
     * @return
     */
    public UserVO queryUserById(String userId){
        UserEntity userEntity = userRepository.findUserEntityByUserId(userId);
        return newsConverter.toUserVO(userEntity);
    }

    /**
     * 如果验证通过则返回个人信息
     * @param userId
     * @param pwd
     * @return
     */
    public UserVO checkUserPWD(String userId, String pwd){
        UserVO userVO = this.queryUserById(userId);
        String passWord = userVO.getPassWord();
        if(!passWord.equals(pwd))
            return null;
        return userVO;
    }


    /**
     * 创建或者更新用户，创建时进行去重查询
     * @param userVO
     * @return
     */
    public ResponseModel<UserVO> createOrUpdateUser(UserVO userVO){
        if(StringUtils.isEmpty(userVO.getUuid()) && StringUtils.isNotEmpty(userVO.getUserId())){
            UserEntity userEntityByUserId = userRepository.findUserEntityByUserId(userVO.getUserId());
            if(Objects.nonNull(userEntityByUserId)){
                return new ResponseModel<UserVO>(ErrorEnum.USER_EXIST.getCode(), ErrorEnum.USER_EXIST.getMsg(), null);
            }
        }
        UserEntity userEntity = newsConverter.toUserEntity(userVO);
        if(StringUtils.isNotEmpty(userVO.getUuid()) && StringUtils.isEmpty(userVO.getPassWord())){
            UserEntity userEntity1 = Optional.ofNullable(userRepository.findUserEntityByUserId(userVO.getUuid())).orElse(new UserEntity());
            userEntity.setPassWord(userEntity1.getPassWord());
        }
        userRepository.save(userEntity);
        UserVO toUserVO = newsConverter.toUserVO(userEntity);
        return new ResponseModel<UserVO>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), toUserVO);
    }

    /**
     * 用户动态查询
     * @param userVO
     * @return
     */
    public List<UserEntity> queryUserList(UserVO userVO){

        Specification<UserEntity> specification = (Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> pList = new ArrayList<>();
            if(StringUtils.isNotEmpty(userVO.getUserId())){
                Predicate predicate = cb.equal(root.get(USER_ID), userVO.getUserId());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(userVO.getName())){
                Predicate predicate = cb.like(root.get(USER_NAME), userVO.getName());
                pList.add(predicate);
            }
            if(StringUtils.isNotEmpty(userVO.getUuid())){
                Predicate predicate = cb.equal(root.get(UUID), userVO.getUuid());
                pList.add(predicate);
            }
            Predicate[] pArray = new Predicate[pList.size()];
            query.where(pList.toArray(pArray));
            return null;
        };
        return userRepository.findAll(specification);
    }

    public ResponseModel<List<UserVO>> queryUserVOList(UserVO userVO){
        List<UserEntity> userEntities = this.queryUserList(userVO);
        List<UserVO> userVOS = newsConverter.toUserVOList(userEntities);
        return new ResponseModel<>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), userVOS);
    }
}
