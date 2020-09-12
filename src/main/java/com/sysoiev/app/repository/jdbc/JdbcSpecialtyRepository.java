package com.sysoiev.app.repository.jdbc;

import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;
import com.sysoiev.app.repository.SpecialtiesRepository;
import com.sysoiev.app.util.ConnectionConfig;
import com.sysoiev.app.util.mappers.SpecialtyMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcSpecialtyRepository implements SpecialtiesRepository {

    @Override
    public Specialty getById(Long aLong) {
        Specialty specialty = new Specialty();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM specialties WHERE Id=?");
            preparedStatement.setLong(1, aLong);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialty = SpecialtyMapper.mapperSpecialty(resultSet);
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

        if (specialty == null) {
            Optional<Specialty> empty = Optional.empty();
            return empty.orElseThrow(NullPointerException::new);
        } else return specialty;
    }

    @Override
    public void deleteById(Long aLong) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM specialties WHERE Id = ?");
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
    public void update(Specialty item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE specialties SET " +
                    "Specialty = ? WHERE Id = ?");

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
    public Specialty save(Specialty item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfig.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO specialties (id,specialty)" +
                    "VALUES (?,?)");
            preparedStatement.setLong(1, item.getId());
            preparedStatement.setString(2, item.getSpecialty());
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
        return item;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfig.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM specialties");

            while (resultSet.next()) {
                specialties.add(SpecialtyMapper.mapperSpecialty(resultSet));
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
