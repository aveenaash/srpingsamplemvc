package com.rab3.repositories;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.rab3.entities.CustomerLoginEntity;
import com.rab3.utils.DbUtils;

public class CustomerLoginRepository {

	public void save(CustomerLoginEntity customerLogin) {
		SessionFactory sessionFactory = DbUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(customerLogin);
		session.getTransaction().commit();
		session.close();
	}
	
	public void update(CustomerLoginEntity customerLogin) {
		SessionFactory sessionFactory = DbUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.update(customerLogin);
		session.getTransaction().commit();
		session.close();
	}
	
	public CustomerLoginEntity getLoginByUserName(String  username) {
		SessionFactory sessionFactory = DbUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from CustomerLoginEntity c where c.userName = :userName");
		query.setParameter("userName", username);
		
		CustomerLoginEntity customerLoginEntity = null;
		try {
			customerLoginEntity = (CustomerLoginEntity) query.getSingleResult();
		} catch(NoResultException ne) {
			System.out.println("no entity found for username : " + username);
			return null;
		}
		
		session.getTransaction().commit();
		session.close();
		
		return customerLoginEntity;
	}
}
