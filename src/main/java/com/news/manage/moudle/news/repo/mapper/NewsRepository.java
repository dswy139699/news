package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.NewsEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<NewsEntity, String>, JpaSpecificationExecutor<NewsEntity> {
    List<NewsEntity> findAllByOrderByClickCountDesc(Pageable pageable);
    List<NewsEntity> findAllByOrderByCreateTimeDesc(Pageable pageable);

    @Query(value = "SELECT id FROM NewsEntity ORDER BY RAND() LIMIT :size ",nativeQuery = true)
    List<NewsEntity> findAllByRandom(@Param("size") int size);
}
