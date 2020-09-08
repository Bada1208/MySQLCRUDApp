package com.sysoiev.app.repository.jdbc;

import com.sysoiev.app.model.Account;
import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;
import com.sysoiev.app.repository.AccountRepository;
import com.sysoiev.app.repository.CustomerRepository;
import com.sysoiev.app.repository.SpecialtiesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JdbcCustomerRepository implements CustomerRepository {
    private SpecialtiesRepository specialtiesRepository = new JdbcSpecialtyRepository();
    private AccountRepository accountRepository = new JdbcAccountRepository();

    @Override
    public Customer getById(Long aLong) {
        //return new Customer.CustomerBuilder(name, surname, specialtySet, account).id(id).buildCustomer();
        Customer customer = new Customer.CustomerBuilder().id(aLong).buildCustomer();
        Account account = new Account();
        Set<Specialty> specialtySet = new HashSet<>();
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
                customer.setId(resultSet.getLong("customers.id"));
                customer.setName(resultSet.getString("customers.name"));
                customer.setSurname(resultSet.getString("customers.surname"));
                // customer.setSpecialties(specialtySet.add(resultSet.getString("customer_specialties.specialties_id")));
                customer.setAccount(customer.getAccount().getId(resultSet.getLong("customers.account_id")));
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

        return customer;
    }

    public Set<Specialty> getByIdCustomerSpecialties(Long idCustomer) {
        Specialty specialty = new Specialty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Set<Specialty> specialties = new HashSet<>();

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM customer_specialties WHERE customer_id=?");
            preparedStatement.setLong(1, idCustomer);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialty.setId(resultSet.getLong("customer_id"));
                specialty.setSpecialty(resultSet.getString("specialty_id"));
                specialties.add(specialty);
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

        return specialties;
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

    }

    public void updateCustomerSpecialties(Specialty item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE customer_specialties SET " +
                    "specialty_id = ? WHERE customer_id = ?");

            preparedStatement.setString(1, item.getSpecialty());
            preparedStatement.setLong(2, item.getId());

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

    public Set<Specialty> saveCustomerSpecialties(Specialty item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Set<Specialty> specialties = new HashSet<>();

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO specialties (id,specialty)" +
                    "VALUES (?,?)");
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setString(2, item.getSpecialty());
            specialties.add(item);
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
        return specialties;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    public Set<Specialty> getAllCustomerSpecialties() {
        Set<Specialty> specialties = new HashSet<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM customer_specialties");

            while (resultSet.next()) {
                Specialty specialty = new Specialty();
                specialty.setId(resultSet.getLong("customer_id"));
                specialty.setSpecialty(resultSet.getString("customer_specialties"));

                specialties.add(specialty);
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

        return specialties;
    }
}
