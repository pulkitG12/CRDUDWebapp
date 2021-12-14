package com.vir.springboot.repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class HibernateSession {

    public Session getSession()
    {
        SessionFactory factory = null;
        Session session = null;
        Configuration configuration = new Configuration().configure();
        factory = configuration.buildSessionFactory();
        session = factory.openSession();
        return session;
    }
}
