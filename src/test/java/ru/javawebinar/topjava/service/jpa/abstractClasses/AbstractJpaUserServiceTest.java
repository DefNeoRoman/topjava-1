package ru.javawebinar.topjava.service.jpa.abstractClasses;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

public abstract class AbstractJpaUserServiceTest extends AbstractUserServiceTest {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
   protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
      super.setUp();
      jpaUtil.clear2ndLevelHibernateCache();
    }
}
