package com.stackroute.JDBC;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;

import java.util.List;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("file:/home/abinash/MavinApp/JDBC/JDBCDemo/src/main/resources/spring.xml");
        SimpleJDBCDemo simpleJDBCDemo=applicationContext.getBean("simpleJDBCDemo",SimpleJDBCDemo.class);
        simpleJDBCDemo.getEmployeeDetails();
        /*while (simpleJDBCDemo.getEmployeName("id").next()){
            System.out.println(simpleJDBCDemo.getEmployeName("id").getString(2));
        }*/

        ArrayList<Persons> persons=(ArrayList<Persons>)simpleJDBCDemo.getAllPersons();
        for (Persons person:persons) {
            System.out.println(person.toString());

        }

        NamedParameterDaoSupportImpl namedParameterDaoSupport=applicationContext.getBean("namedParameterDaoSupportImpl",NamedParameterDaoSupportImpl.class);
         // namedParameterDaoSupport.deletePersonDetailsUsingNamedTemplate(22);
        // namedParameterDaoSupport.insertPersonDetailsUsingNamedTemplate(new Persons(24,"kayum","Kana","Andheri East","Mumbai"));
       // simpleJDBCDemo.insertPersonDetails(new Persons(24,"Sharma","rajat","Bangalore","Bangalore"));
        // simpleJDBCDemo.insertPersonDetailsUsingNamedTemplate(new Persons(24,"Babu","Jalela","Andheri East","Mumbai"));
        namedParameterDaoSupport.createTable("CREATE TABLE Persons2 (" +
                "    PersonID int," +
                "    LastName varchar(255)," +
                "    FirstName varchar(255)," +
                "    Address varchar(255)," +
                "    City varchar(255) " +
                ");");

    }
}
