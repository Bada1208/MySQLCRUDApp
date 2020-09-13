package com.sysoiev.app.repository.jdbc;

import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;
import com.sysoiev.app.repository.AccountRepository;
import com.sysoiev.app.repository.CustomerRepository;
import com.sysoiev.app.repository.SpecialtiesRepository;
import com.sysoiev.app.util.ConnectionConfig;
import com.sysoiev.app.util.mappers.CustomerMapper;

import java.sql.*;
import java.util.*;

public class JdbcCustomerRepository implements CustomerRepository {
    private SpecialtiesRepository specialtiesRepository = new JdbcSpecialtyRepository();
    private AccountRepository accountRepository = new JdbcAccountRepository();

    @Override
    public Customer getById(Long aLong) {
        Customer customer = new Customer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.
                    prepareStatement(
                            "select customers.id, customers.name, customers.surname, customer_specialties.specialty_id, customers.account_id\n" +
                                    "from customers\n" +
                                    "         join customer_specialties on\n" +
                                    "         customer_specialties.customer_id = customers.id where customers.id=?;");
            preparedStatement.setLong(1, aLong);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = CustomerMapper.mapperCustomer(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (customer == null) {
            Optional<Customer> empty = Optional.empty();
            return empty.orElseThrow(NullPointerException::new);
        } else return customer;
    }

    @Override
    public void deleteById(Long aLong) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE Id = ?");
            preparedStatement.setLong(1, aLong);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Customer item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE customers SET " +
                    "name=?,surname=?,account_id = ? WHERE Id = ?");

            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getSurname());
            preparedStatement.setLong(3, item.getAccount().getId());
            preparedStatement.setLong(4, item.getId());
            Set<Specialty> specialtySet = updateCustomerSpecialties(item.getCustomerSpecialties());
            item.setSpecialties(specialtySet);


            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Set<Specialty> updateCustomerSpecialties(Set<Specialty> specialtySet) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        specialtySet = new HashSet<>();
        Specialty item = new Specialty();
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE customer_specialties SET " +
                    "specialty_id = ? WHERE customer_id = ?");

            preparedStatement.setString(1, item.getSpecialty());
            preparedStatement.setLong(2, item.getId());
            specialtySet.add(item);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return specialtySet;
    }

    @Override
    public Customer save(Customer item) {
        Connection connection = null;
        PreparedStatement preparedStatementCustomer = null;
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatementCustomer = connection.prepareStatement("INSERT INTO customers (id,name,surname,account_id)" +
                    "VALUE (?,?,?,?)");
            preparedStatementCustomer.setLong(1, item.getId());
            preparedStatementCustomer.setString(2, item.getName());
            preparedStatementCustomer.setString(3, item.getSurname());
            preparedStatementCustomer.setLong(4, item.getAccount().getId());
            preparedStatementCustomer.executeUpdate();
            saveCustomerSpecialties(item.getCustomerSpecialties());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatementCustomer != null) {
                try {
                    preparedStatementCustomer.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }

    public Set<Specialty> saveCustomerSpecialties(Set<Specialty> customerSpecialties) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        customerSpecialties = new HashSet<>();
        Specialty item = new Specialty();
        try {

            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO customer_specialties (customer_id,specialty_id)" +
                    "VALUES (?,?)");
            preparedStatement.setLong(1, item.getCustomerId());
            preparedStatement.setLong(2, item.getId());
            customerSpecialties.add(item);
            preparedStatement.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return customerSpecialties;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "select customers.id, customers.name, customers.surname, customer_specialties.specialty_id, customers.account_id\n" +
                            "from customers\n" +
                            "         join customer_specialties on\n" +
                            "         customer_specialties.customer_id = customers.id ;");

            while (resultSet.next()) {
                customerList.add(CustomerMapper.mapperCustomer(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return customerList;
    }

}
