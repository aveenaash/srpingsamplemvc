package com.rab3.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbUtils {

	public static SessionFactory sessionFactory = null;
	
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}
	
}
