package com.sso.business.service;

import com.sso.entity.auto.model.SysResource;

/**
 * Created by yt on 2017-7-10.
 */
public interface SysResourceService {
	SysResource queryById(Integer id);

	SysResource queryByResourcePath(String resourcePath);
}
