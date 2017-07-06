package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysModuleDao;
import com.sso.entity.auto.mapper.SysModuleMapper;
import com.sso.entity.auto.model.SysModule;
import com.sso.entity.auto.model.SysModuleExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysModuleDaoImpl extends BasePKDaoAdapter<Integer, SysModule, SysModuleExample, SysModuleMapper> implements SysModuleDao {
}