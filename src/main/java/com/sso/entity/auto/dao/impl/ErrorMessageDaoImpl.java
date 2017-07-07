package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.ErrorMessageDao;
import com.sso.entity.auto.mapper.ErrorMessageMapper;
import com.sso.entity.auto.model.ErrorMessage;
import com.sso.entity.auto.model.ErrorMessageExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class ErrorMessageDaoImpl extends BasePKDaoAdapter<Integer, ErrorMessage, ErrorMessageExample, ErrorMessageMapper> implements ErrorMessageDao {
}