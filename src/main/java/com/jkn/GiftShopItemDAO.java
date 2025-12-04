package com.jkn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public record GiftShopItemDAO(Connection connection) {

    public void create(GiftShopItem giftShopItem) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO gift_shop_items (id, name, price, description, picture) VALUES (?, ?, ?, ?, ?)");
        statement.setLong(1, giftShopItem.id());
        statement.setString(2, giftShopItem.name());
        statement.setInt(3, giftShopItem.price());
        statement.setString(4, giftShopItem.description());
        statement.setBlob(5, giftShopItem.picture());
        statement.execute();
    }

    public GiftShopItem read(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM gift_shop_items WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new GiftShopItem(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("description"),
                    resultSet.getBlob("picture")
            );
        } else {
            return null;
        }
    }

    public void update(GiftShopItem giftShopItem) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE gift_shop_items SET name = ?, price = ?, description = ?, picture = ? WHERE id = ?");
        statement.setString(1, giftShopItem.name());
        statement.setInt(2, giftShopItem.price());
        statement.setString(3, giftShopItem.description());
        statement.setBlob(4, giftShopItem.picture());
        statement.setLong(5, giftShopItem.id());
        statement.execute();
    }

    public void delete(long id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM gift_shop_items WHERE id = ?");
        statement.setLong(1, id);
        statement.execute();
    }
}
