package com.icustomer.controller;

import com.icustomer.entity.Customer;
import com.icustomer.service.CustomerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
    // Inject Customer DAO
    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/list")
    public String listCustomer(Model model) {
        
        // get customer from dao
        List<Customer> customers = customerService.getCustomers();
        
        // add customer to model
        model.addAttribute("customers", customers);
        
        return "list-customers";
    }
    
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer = new Customer();
        
        model.addAttribute("customer", customer);
        
        
        return "customer-form";
    }
    
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        
        return "redirect:/customer/list";
    }
    
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
        
        Customer customer = customerService.getCustomer(customerId);
        
        model.addAttribute("customer", customer);
        
        return "customer-form";
    }
    
    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int customerId) {
        customerService.deleteCustomer(customerId);
        
        return "redirect:/customer/list";
    }
}
