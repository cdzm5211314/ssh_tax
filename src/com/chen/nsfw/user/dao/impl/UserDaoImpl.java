package com.chen.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.chen.core.dao.impl.BaseDaoImpl;
import com.chen.nsfw.user.dao.UserDao;
import com.chen.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public List<User> findUserByAccountAndId(String id, String account) {

		String  hql = "FROM User WHERE account = ?";
		if (StringUtils.isNotBlank(id)) {
			hql += " AND id != ?";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		if (StringUtils.isNotBlank(id)) {
			query.setParameter(1, id);
		}
		return query.list();
	}

}
