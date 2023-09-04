package com.epam.rd.autocode;

import com.epam.rd.autocode.domain.Employee;
import com.epam.rd.autocode.domain.FullName;
import com.epam.rd.autocode.domain.Position;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RowMapperImpl implements RowMapper<Employee> {
    private static final Logger LOGGER = LogManager.getLogger(RowMapperImpl.class);
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String MIDDLE_NAME = "middleName";
    private static final String POSITION = "position";
    private static final String HIREDATE = "hiredate";
    private static final String SALARY = "salary";

    @Override
    public Employee mapRow(ResultSet resultSet) {
        try {
            FullName fullname = new FullName(
                    resultSet.getString(FIRSTNAME),
                    resultSet.getString(LASTNAME),
                    resultSet.getString(MIDDLE_NAME));

            return new Employee(
                    new BigInteger(resultSet.getString(ID)),
                    fullname,
                    Position.valueOf(resultSet.getString(POSITION)),
                    resultSet.getDate(HIREDATE).toLocalDate(),
                    resultSet.getBigDecimal(SALARY));
        } catch (SQLException e) {
            LOGGER.error("Something went wrong at mapRow!!!", e);
            throw new RuntimeException(e);
        }
    }
}