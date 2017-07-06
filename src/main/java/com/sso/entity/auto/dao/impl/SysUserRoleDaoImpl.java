package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysUserRoleDao;
import com.sso.entity.auto.mapper.SysUserRoleMapper;
import com.sso.entity.auto.model.SysUserRole;
import com.sso.entity.auto.model.SysUserRoleExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysUserRoleDaoImpl extends BasePKDaoAdapter<Long, SysUserRole, SysUserRoleExample, SysUserRoleMapper> implements SysUserRoleDao {
}