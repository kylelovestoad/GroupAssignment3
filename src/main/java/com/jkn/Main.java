package com.jkn;

import com.jkn.controller.GiftShopItemService;
import com.jkn.model.dao.GiftShopItemDAO;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main() throws Exception {
        Scanner scanner = new Scanner(System.in);

        GiftShopItemService dao = new GiftShopItemService();

        dao.createGiftShopItem("dk", 1, "abc", new SerialBlob(new byte[0]));
    }
}
