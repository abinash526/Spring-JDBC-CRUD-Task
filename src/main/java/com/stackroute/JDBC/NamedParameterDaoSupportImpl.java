package com.stackroute.JDBC;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class NamedParameterDaoSupportImpl extends NamedParameterJdbcDaoSupport {
    public void insertPersonDetailsUsingNamedTemplate(Persons persons)
    {
        String sql="Insert into Persons Values(:id,:lastName,:firstName,:address,:city)";
        SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",persons.getId()).addValue("lastName",persons.getLastName()).addValue("firstName",persons.getFirstName()).addValue("address",persons.getAddress()).addValue("city",persons.getCity());
        this.getNamedParameterJdbcTemplate().update(sql,sqlParameterSource);
    }
    public void deletePersonDetailsUsingNamedTemplate(Integer  personId)
    {
        String sql="DELETE FROM Persons WHERE PersonID=?";
       // SqlParameterSource sqlParameterSource=new MapSqlParameterSource("id",personId);
        this.getJdbcTemplate().update(sql,new Object[]{personId});
    }
    public void createTable(String sql){
        this.getJdbcTemplate().execute(sql);
    }
}
