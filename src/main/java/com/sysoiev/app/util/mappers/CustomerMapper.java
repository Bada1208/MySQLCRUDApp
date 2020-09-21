package com.sysoiev.app.util.mappers;

import com.sysoiev.app.model.Account;
import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerMapper {
    public static Customer mapperCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        Account account = new Account();
        Specialty specialty = new Specialty();
        customer.setId(resultSet.getLong("customers.id"));
        customer.setName(resultSet.getString("customers.name"));
        customer.setSurname(resultSet.getString("customers.surname"));
        account.setId(resultSet.getLong("customers.account_id"));
        customer.setAccount(account);
        Set<Specialty> specialtySet = new HashSet<>();
        while (resultSet.next()) {
            long customerSpecialtyId = resultSet.getLong("customer_specialties.customer_id");
            long specialtyId = resultSet.getLong("customer_specialties.specialty_id");
            if (customerSpecialtyId == customer.getId()) {
                specialty.setId(specialtyId);
                specialtySet.add(specialty);
            }
        }
        // specialtySet.add(SpecialtyMapper.mapperSpecialtyId(resultSet));
        customer.setCustomerSpecialtiesSet(specialtySet);
        return customer;
    }
}
