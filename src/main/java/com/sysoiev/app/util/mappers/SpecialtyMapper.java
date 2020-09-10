package com.sysoiev.app.util.mappers;

import com.sysoiev.app.model.Specialty;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialtyMapper {
    public static Specialty mapperSpecialty(ResultSet resultSet) throws SQLException {
        Specialty specialty=new Specialty();
        specialty.setId(resultSet.getLong("Id"));
        specialty.setSpecialty(resultSet.getString("Specialty"));
        return specialty;
    }
    public static Specialty mapperSpecialtyId(ResultSet resultSet) throws SQLException {
        Specialty specialty=new Specialty();
        specialty.setId(resultSet.getLong("Id"));
        return specialty;
    }
}
