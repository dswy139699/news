package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepository extends JpaRepository<CommentEntity, String>, JpaSpecificationExecutor<CommentEntity> {
    CommentEntity findByUuid(String uuid);
}
