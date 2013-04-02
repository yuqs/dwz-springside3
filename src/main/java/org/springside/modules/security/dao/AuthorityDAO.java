package org.springside.modules.security.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;
import org.springside.modules.security.entity.Authority;

@Component
public class AuthorityDAO extends HibernateDao<Authority, Long> {

}
