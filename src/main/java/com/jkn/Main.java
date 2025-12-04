package com.jkn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    static void main() {
        // TODO set this connection
        try(Connection connection = DriverManager.getConnection("")) {
            GiftShopItemDAO sectionService = new GiftShopItemDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
