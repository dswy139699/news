package com.news.manage.moudle.news.repo.mapper;

import com.news.manage.moudle.news.repo.dao.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, String> {
}
