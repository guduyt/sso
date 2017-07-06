package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysRoleResourceDao;
import com.sso.entity.auto.mapper.SysRoleResourceMapper;
import com.sso.entity.auto.model.SysRoleResource;
import com.sso.entity.auto.model.SysRoleResourceExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysRoleResourceDaoImpl extends BasePKDaoAdapter<Integer, SysRoleResource, SysRoleResourceExample, SysRoleResourceMapper> implements SysRoleResourceDao {
}