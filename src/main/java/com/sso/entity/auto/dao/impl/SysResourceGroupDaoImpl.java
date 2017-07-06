package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysResourceGroupDao;
import com.sso.entity.auto.mapper.SysResourceGroupMapper;
import com.sso.entity.auto.model.SysResourceGroup;
import com.sso.entity.auto.model.SysResourceGroupExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysResourceGroupDaoImpl extends BasePKDaoAdapter<Integer, SysResourceGroup, SysResourceGroupExample, SysResourceGroupMapper> implements SysResourceGroupDao {
}