package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findUserEntityByUserId(String uuid);
}
