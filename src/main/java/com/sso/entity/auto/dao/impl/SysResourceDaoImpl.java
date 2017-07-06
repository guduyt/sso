package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysResourceDao;
import com.sso.entity.auto.mapper.SysResourceMapper;
import com.sso.entity.auto.model.SysResource;
import com.sso.entity.auto.model.SysResourceExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysResourceDaoImpl extends BasePKDaoAdapter<Integer, SysResource, SysResourceExample, SysResourceMapper> implements SysResourceDao {
}