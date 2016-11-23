package com.suman.ecom.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.suman.ecom.model.Product;


@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	
	@Autowired
	Product product;
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean delete(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	public Product get(int id) {
		
	
		String hql="from Product where prod_id="+id;
		//Session s = sessionFactory.getCurrentSession();
		//Transaction t = s.beginTransaction();
		
		
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Product> list=query.list();
		if(list==null)
		{
			return null;
			
		}
		else{
		//	t.commit();
			return list.get(0);


		}
		
	}

	
	public ProductDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	@Transactional

	public boolean savOrUpdate(Product product)
	{
		try {

			Session s = sessionFactory.getCurrentSession();
			Transaction t = s.beginTransaction();
			s.saveOrUpdate(product);
			t.commit();

			
			//sessionFactory.getCurrentSession().save(product);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	@Transactional
	//retrieving list of product
	public List<Product> list() {
		Session s=sessionFactory.getCurrentSession();
		Transaction tx=s.beginTransaction();
		//createCriteria is a depricated method(it helps in getting products list based on criteria)
		List<Product> list=s.createCriteria(Product.class).list();
		
		return list;
	}

	
	
	
}
