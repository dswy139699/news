package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FIleRepository extends JpaRepository<FileEntity, String> {
}
