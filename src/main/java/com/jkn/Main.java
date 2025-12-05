package com.jkn;

import com.jkn.controller.GiftShopItemService;
import com.jkn.view.CLI;

import javax.sql.rowset.serial.SerialBlob;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        try {
//            GiftShopItemService service = new GiftShopItemService();
////            service.createGiftShopItem("dk", 1, "abc", new SerialBlob(new byte[0]));
//
//            System.out.println(service.getAllItems());
            new CLI().run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
