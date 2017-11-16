package com.sso.entity.auto.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysResourceDao;
import com.sso.entity.auto.mapper.SysResourceMapper;
import com.sso.entity.auto.model.SysResource;
import com.sso.entity.auto.model.SysResourceExample;
import com.sso.yt.commons.constants.error.code.DaoErrorCode;
import com.sso.yt.commons.utils.Assert;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysResourceDaoImpl extends BasePKDaoAdapter<Integer, SysResource, SysResourceExample, SysResourceMapper> implements SysResourceDao {

	@Override
	public SysResource queryByResourcePath(String resourcePath) {
		SysResourceExample example = new SysResourceExample();
		example.createCriteria().andResourcePathEqualTo(resourcePath);

		List<SysResource> list = this.selectByExample(example);

		Assert.isTrue(list.size() < 2, DaoErrorCode.CODE_1002001, "查询唯一资源url存在多条数据！");
		if (list.size() == 0)
			return null;
		return list.get(0);
	}
}