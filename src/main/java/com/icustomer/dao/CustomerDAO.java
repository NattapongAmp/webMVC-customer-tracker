package com.icustomer.dao;

import com.icustomer.entity.Customer;
import java.util.List;

public interface CustomerDAO {
    
    public List<Customer> getCustomers();
    
    public void saveCustomer(Customer customer);
    
    public Customer getCustomer(int customerId);
    
    public void deleteCustomer(int customerId);
}
