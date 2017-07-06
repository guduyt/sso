package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysModuleResourceDao;
import com.sso.entity.auto.mapper.SysModuleResourceMapper;
import com.sso.entity.auto.model.SysModuleResource;
import com.sso.entity.auto.model.SysModuleResourceExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysModuleResourceDaoImpl extends BasePKDaoAdapter<Integer, SysModuleResource, SysModuleResourceExample, SysModuleResourceMapper> implements SysModuleResourceDao {
}