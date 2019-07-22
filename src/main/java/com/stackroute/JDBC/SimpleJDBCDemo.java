package com.stackroute.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.List;

@Component("simpleJDBCDemo")
public class SimpleJDBCDemo {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private Connection connection;
    Statement statement;
    ResultSet resultSet;

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public SimpleJDBCDemo setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
        return this;
    }

    public void getEmployeeDetails()
    {
        /*try {

           connection=dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try(
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("Select * FROM EMPLOYEE");) {*/

            /*Use Drivermanager to get Connection*/

        //jdbcTemplate.setDataSource(getDataSource());

        SqlRowSet sqlRowSet=jdbcTemplate.queryForRowSet("Select * FROM Persons");

            while (sqlRowSet.next())
            {
                System.out.println("  "+sqlRowSet.getInt(1)+"  "+sqlRowSet.getString(3));
            }
           /* statement.close();
            resultSet.close();



        } catch (SQLException exception) {
            exception.printStackTrace();
        }*/
    }
    public void insertPersonDetails(Persons persons)
    {
        String sql="Insert into Persons Values(?,?,?,?,?)";
        jdbcTemplate.update(sql,new Object[]{persons.getId(),persons.getLastName(),persons.getFirstName(),persons.getAddress(),persons.getCity()});

    }
    public void insertPersonDetailsUsingNamedTemplate(Persons persons)
    {
        String sql="Insert into Persons Values(:id,:lastName,:firstName,:address,:city)";
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",persons.getId()).addValue("lastName",persons.getLastName()).addValue("firstName",persons.getFirstName()).addValue("address",persons.getAddress()).addValue("city",persons.getCity());
        namedParameterJdbcTemplate.update(sql,sqlParameterSource);
    }
    public List<Persons> getAllPersons()
    {
        return jdbcTemplate.query("Select * From Persons",new PersonMapper());
    }
    public static final class PersonMapper implements RowMapper<Persons>{

        @Override
        public Persons mapRow(ResultSet rs, int rowNum) throws SQLException {
            Persons persons=new Persons();
            persons.setAddress(rs.getString(4));
            persons.setCity(rs.getString(5));
            persons.setId(rs.getInt(1));
            persons.setFirstName(rs.getString(3));
            persons.setLastName(rs.getString(2));

            return persons;
        }
    }


}
