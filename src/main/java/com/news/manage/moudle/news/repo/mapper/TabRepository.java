package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.TabEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TabRepository extends JpaRepository<TabEntity,String>, JpaSpecificationExecutor<TabEntity> {
    TabEntity findByUuid(String uuid);
}
