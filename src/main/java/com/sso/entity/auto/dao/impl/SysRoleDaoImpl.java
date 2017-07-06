package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysRoleDao;
import com.sso.entity.auto.mapper.SysRoleMapper;
import com.sso.entity.auto.model.SysRole;
import com.sso.entity.auto.model.SysRoleExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysRoleDaoImpl extends BasePKDaoAdapter<Integer, SysRole, SysRoleExample, SysRoleMapper> implements SysRoleDao {
}