package com.sysoiev.app.repository.jdbc;

import com.sysoiev.app.model.Customer;
import com.sysoiev.app.repository.CustomerRepository;

import java.util.List;

public class JdbcCustomerRepository implements CustomerRepository {
    @Override
    public Customer getById(Long aLong) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void update(Customer item) {

    }

    @Override
    public Customer save(Customer item) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
