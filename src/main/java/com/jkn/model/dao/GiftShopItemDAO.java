package com.jkn.model.dao;

import com.jkn.model.entity.GiftShopItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GiftShopItemDAO extends AbstractDAO<GiftShopItem> {

    @Override
    public void create(GiftShopItem giftShopItem) throws SQLException {
        Connection con = getConnection();

        PreparedStatement statement = con.prepareStatement("INSERT INTO GiftShopItem (name, price, description, image) VALUES (?, ?, ?, ?)");
        statement.setString(1, giftShopItem.getName());
        statement.setInt(2, giftShopItem.getPrice());
        statement.setString(3, giftShopItem.getDescription());
        statement.setBlob(4, giftShopItem.getPicture());
        statement.executeUpdate();

        ResultSet rs = statement.getGeneratedKeys();
        if (rs.next()) {
            giftShopItem.setID(rs.getLong(1));
        }

        con.close();

    }

    @Override
    public GiftShopItem read(long id) throws SQLException {
        Connection con = getConnection();

        PreparedStatement pst = getConnection().prepareStatement("SELECT * FROM GiftShopItem WHERE gift_shop_item_id = ?");
        pst.setLong(1, id);

        ResultSet Rs = pst.executeQuery();
        GiftShopItem readItem = null;

        if (Rs.next()) {
            readItem = new GiftShopItem(
                Rs.getString("name"),
                Rs.getInt("price"),
                Rs.getString("description"),
                Rs.getBlob("image")
            );
            readItem.setID(Rs.getLong("gift_shop_item_id"));
        }

        con.close();
        return readItem;
    }

    @Override
    public void update(GiftShopItem giftShopItem) throws SQLException {
        Connection con = getConnection();

        PreparedStatement statement = con.prepareStatement("UPDATE GiftShopItem SET name = ?, price = ?, description = ?, image = ? WHERE gift_shop_item_id = ?");
        statement.setString(1, giftShopItem.getName());
        statement.setInt(2, giftShopItem.getPrice());
        statement.setString(3, giftShopItem.getDescription());
        statement.setBlob(4, giftShopItem.getPicture());
        statement.setLong(5, giftShopItem.getID());
        statement.execute();

        con.close();
    }

    @Override
    public void delete(long id) throws SQLException {
        System.out.println("Id: " + id);

        Connection con = getConnection();

        PreparedStatement statement = con.prepareStatement("DELETE FROM GiftShopItem WHERE gift_shop_item_id = ?");
        statement.setLong(1, id);
        statement.execute();

        con.close();
    }

    @Override
    public List<GiftShopItem> list() throws SQLException {
        Connection con = getConnection();

        ArrayList<GiftShopItem> lstGiftShopItem = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM GiftShopItem ORDER BY name");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            GiftShopItem item = new GiftShopItem(
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("description"),
                rs.getBlob("image")
            );
            item.setID(rs.getLong("gift_shop_item_id"));

            lstGiftShopItem.add(item);
        }

        con.close();

        return lstGiftShopItem;
    }
}
