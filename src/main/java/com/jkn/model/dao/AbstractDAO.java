package com.jkn.model.dao;


import com.jkn.model.entity.AbstractEntity;

import java.sql.*;
import java.util.List;

public abstract class AbstractDAO<E extends AbstractEntity> {
    private String ConUrl = "jdbc:mariadb://localhost"; //protocol + url
    private String Port = "3306"; //default MySQL port
    private String Database = "zoo"; // database/schema name
    private String Username = "root"; //read this from a local file
    private String Password = "dk"; //Also read this from a file

    public Connection getConnection() throws SQLException {
        System.out.println("Hello World");
        return DriverManager.getConnection(ConUrl+":"+Port+"/"+Database+ "?user="+Username+"&password="+Password);
    }

    public abstract void create(E entity) throws SQLException;
    public abstract E read(long ID) throws SQLException;
    public abstract void update(E entity) throws SQLException;
    public abstract void delete(long ID) throws SQLException;
    public abstract List<E> list() throws SQLException;
}
