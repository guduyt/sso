package com.sso.entity.auto.dao.impl;

import org.springframework.stereotype.Repository;

import com.sso.entity.auto.dao.SysUserDao;
import com.sso.entity.auto.mapper.SysUserMapper;
import com.sso.entity.auto.model.SysUser;
import com.sso.entity.auto.model.SysUserExample;
import com.yt.mybatis.dao.BasePKDaoAdapter;

@Repository
public class SysUserDaoImpl extends BasePKDaoAdapter<Long, SysUser, SysUserExample, SysUserMapper> implements SysUserDao {

	/*public SysUser queryUserByUserName(String userName) {
		SysUserExample sysUserExample =new SysUserExample();
		sysUserExample.createCriteria().andUserNameEqualTo(userName);
		List<SysUser>  list= selectByExample(sysUserExample);

		return ;
	}*/
}