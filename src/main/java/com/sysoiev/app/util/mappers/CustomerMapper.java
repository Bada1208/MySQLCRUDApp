package com.sysoiev.app.util.mappers;

import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CustomerMapper {
    public static Customer mapperCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("customers.id"));
        customer.setName(resultSet.getString("customers.name"));
        customer.setSurname(resultSet.getString("customers.surname"));
        Set<Specialty> specialtySet = new HashSet<>();
        specialtySet.add(SpecialtyMapper.mapperSpecialtyId(resultSet));
        customer.setSpecialties(specialtySet);
        customer.setAccount(AccountMapper.mapperAccountId(resultSet));
        return customer;
    }
}
