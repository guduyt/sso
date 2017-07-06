package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysResourceActionDao;
import com.sso.entity.auto.mapper.SysResourceActionMapper;
import com.sso.entity.auto.model.SysResourceAction;
import com.sso.entity.auto.model.SysResourceActionExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysResourceActionDaoImpl extends BasePKDaoAdapter<Integer, SysResourceAction, SysResourceActionExample, SysResourceActionMapper> implements SysResourceActionDao {
}