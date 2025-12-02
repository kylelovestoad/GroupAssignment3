package com.jkn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO Class for Section Entity
 */
public class SectionService {

    private final Connection connection;
    public SectionService(Connection connection) {
        this.connection = connection;
    }

    public void create(Section section) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO sections (id, name) VALUES (?, ?)");
        statement.setLong(1, section.id());
        statement.setString(2, section.name());
        statement.execute();
    }

    public Section read(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM sections WHERE id == ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {
            return new Section(
                    resultSet.getLong("id"),
                    resultSet.getString("name")
            );
        } else {
            return null;
        }
    }

    public void update(Section section) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE sections SET name = ? WHERE id = ?");
        statement.setString(1, section.name());
        statement.setLong(2, section.id());
        statement.execute();
    }

    public void delete(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM sections WHERE id = ?");
        statement.setLong(1, id);
        statement.execute();
    }
}
