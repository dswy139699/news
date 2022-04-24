package com.news.manage.moudle.news.repo.dao;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "NEWS_ROLE")
public class RoleEntity extends BaseEntity{
    private String roleCode;
    private String roleName;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
