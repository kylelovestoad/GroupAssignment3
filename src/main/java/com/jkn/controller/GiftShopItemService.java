package com.jkn.controller;

import com.jkn.model.dao.GiftShopItemDAO;
import com.jkn.model.entity.GiftShopItem;

import java.sql.Blob;
import java.util.List;

public class GiftShopItemService {
    public GiftShopItem createGiftShopItem(String name, int price, String description, Blob picture) throws Exception {

        GiftShopItem item = new GiftShopItem(name, price, description, picture);

        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.create(item);

        return item;
    }

    public GiftShopItem updateGiftShopItemName(GiftShopItem item, String name) throws Exception {

        item.setName(name);

        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.update(item);

        return item;
    }

    public GiftShopItem updateGiftShopItemPrice(GiftShopItem item, int price) throws Exception {

        item.setPrice(price);

        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.update(item);

        return item;
    }

    public GiftShopItem updateGiftShopItemDescription(GiftShopItem item, String description) throws Exception {

        item.setDescription(description);

        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.update(item);

        return item;
    }

    public List<GiftShopItem> getAllItems() throws Exception {
        GiftShopItemDAO dao = new GiftShopItemDAO();
        return dao.list();
    }

    public void deleteGiftShopItem(int id) throws Exception {
        GiftShopItemDAO dao = new GiftShopItemDAO();
        dao.delete(id);
    }
}
