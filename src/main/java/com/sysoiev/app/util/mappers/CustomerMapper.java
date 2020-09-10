package com.sysoiev.app.util.mappers;

import com.sysoiev.app.model.Account;
import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public static Customer mapperCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("customers.id"));
        customer.setName(resultSet.getString("customers.name"));
        customer.setSurname(resultSet.getString("customers.surname"));
        Specialty specialty = SpecialtyMapper.mapperSpecialty(resultSet);
        customer.setSpecialties(specialty);
        Account account = AccountMapper.mapperAccount(resultSet);
        customer.setAccount(account);
        return customer;
    }
}
