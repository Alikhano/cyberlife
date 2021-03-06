package ru.alikhano.cyberlife.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.model.Cart;

@Repository
public class CartDaoImpl extends GenericDaoImpl<Cart> implements CartDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public int createAndGetId(Cart cart) {
		return (Integer) sessionFactory.getCurrentSession().save(cart);
	}

	@Override
	public void merge(Cart cart) {
		 Cart cartToSave = (Cart) sessionFactory.getCurrentSession().merge(cart);
		  sessionFactory.getCurrentSession().save(cartToSave);
	}
}
