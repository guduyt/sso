package com.sso.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sso.business.service.SysResourceService;
import com.sso.entity.auto.dao.SysResourceDao;
import com.sso.entity.auto.model.SysResource;

/**
 * Created by yt on 2017-7-10.
 */
@Service
public class SysResourceServiceImpl implements SysResourceService{

	@Autowired
	private SysResourceDao sysResourceDao;

	public SysResource queryById(Integer id){
		return sysResourceDao.selectByPrimaryKey(id);
	}

	public SysResource queryByResourcePath(String resourcePath){
		return sysResourceDao.queryByResourcePath(resourcePath);
	}

}
