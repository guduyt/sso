package com.sso.entity.auto.dao;

import com.yt.mybatis.dao.BasePKDao;
import com.sso.entity.auto.model.SysResource;
import com.sso.entity.auto.model.SysResourceExample;

public interface SysResourceDao extends BasePKDao<Integer, SysResource, SysResourceExample> {
	SysResource queryByResourcePath(String resourcePath);

}