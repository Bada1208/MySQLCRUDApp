package com.sysoiev.app.controller;

import com.sysoiev.app.model.Customer;
import com.sysoiev.app.repository.CustomerRepository;
import com.sysoiev.app.repository.jdbc.JdbcCustomerRepository;

import java.util.List;

public class CustomerController  {
    private CustomerRepository customerRepository = new JdbcCustomerRepository();

    public List<Customer> printAll() {
        return customerRepository.getAll();
    }

    public void saveCustomer(Customer newCustomer) {
        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Long index) {
        customerRepository.deleteById(index);
    }

    public void updateCustomer(Customer updateCustomer) {
        customerRepository.update(updateCustomer);
    }

    public Customer getValueByIndex(Long index) {
        return customerRepository.getById(index);
    }
}
