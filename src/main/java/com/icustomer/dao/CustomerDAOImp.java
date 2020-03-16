package com.icustomer.dao;

import com.icustomer.entity.Customer;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImp implements CustomerDAO {
    
    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;
			
    @Override
    public List<Customer> getCustomers() {
		
	// get the current hibernate session
	Session currentSession = sessionFactory.getCurrentSession();
				
	// create a query
	Query<Customer> theQuery = 
            currentSession.createQuery("from Customer order by lastName", Customer.class);
		
	// execute query and get result list
	List<Customer> customers = theQuery.getResultList();
				
	// return the results		
	return customers;
    }

    @Override
    public void saveCustomer(Customer customer) {
        
        Session currentSession = sessionFactory.getCurrentSession();
        
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        
        Customer customer = currentSession.get(Customer.class, customerId);
        
        return customer;
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        
        query.setParameter("customerId", customerId);
        
        query.executeUpdate();
    }
    
}
