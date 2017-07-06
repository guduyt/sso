package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysResourceGroupRelationDao;
import com.sso.entity.auto.mapper.SysResourceGroupRelationMapper;
import com.sso.entity.auto.model.SysResourceGroupRelation;
import com.sso.entity.auto.model.SysResourceGroupRelationExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysResourceGroupRelationDaoImpl extends BasePKDaoAdapter<Integer, SysResourceGroupRelation, SysResourceGroupRelationExample, SysResourceGroupRelationMapper> implements SysResourceGroupRelationDao {
}