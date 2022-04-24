package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<NewsEntity, String>, JpaSpecificationExecutor<NewsEntity> {
}
