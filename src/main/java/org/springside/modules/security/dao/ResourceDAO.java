package org.springside.modules.security.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.entity.Resource;

@Component
public class ResourceDAO extends HibernateDao<Resource, Long> {

}
