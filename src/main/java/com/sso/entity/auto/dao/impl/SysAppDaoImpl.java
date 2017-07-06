package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysAppDao;
import com.sso.entity.auto.mapper.SysAppMapper;
import com.sso.entity.auto.model.SysApp;
import com.sso.entity.auto.model.SysAppExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysAppDaoImpl extends BasePKDaoAdapter<Integer, SysApp, SysAppExample, SysAppMapper> implements SysAppDao {
}