package com.jkn.view;

import com.jkn.controller.GiftShopItemService;
import com.jkn.model.entity.GiftShopItem;

import javax.sql.rowset.serial.SerialBlob;
import java.util.Scanner;

public class CLI {
    private static final String CREATE = "1";
    private static final String DELETE = "2";
    private static final String UPDATE = "3";
    private static final String QUIT = "0";

    private static final String NAME = "1";
    private static final String PRICE = "2";
    private static final String DESCRIPTION = "3";

    GiftShopItemService service = new GiftShopItemService();
    Scanner scanner = new Scanner(System.in);

    private String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private float getFloatInput(String prompt) {

        System.out.print(prompt);

        while (true) {
            if (scanner.hasNextFloat()) {
                float f = scanner.nextFloat();
                scanner.nextLine();
                return f;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private int getIntInput(String prompt) {

        System.out.print(prompt);

        while (true) {
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private void handleCreate() {
        try {
            service.createGiftShopItem(
                getStringInput("Name: "),
                (int) (getFloatInput("Price: ") * 100),
                getStringInput("Description: "),
                new SerialBlob(new byte[0])
            );
        } catch (Exception e) {
            System.out.println("Failed to create gift shop item");
        }
    }

    private void handleDelete() {
        try {
            service.deleteGiftShopItem(getIntInput("Enter number of item to delete"));
        } catch (Exception e) {
            System.out.println("Failed to delete gift shop item");
        }
    }

//    private void handleUpdate() {
//        try {
//            String field = getStringInput("Enter field to update:\n1. Name\n2. Price\n3. Description");
//
//            switch (field) {
//                case NAME -> handleCreate();
//                case PRICE -> handleDelete();
//                case DESCRIPTION ->
//                default -> System.out.println("Invalid input");
//            }
//
//        } catch (Exception e) {
//            System.out.println("Failed to update gift shop item");
//        }
//    }

    public void run() throws Exception {

        while (true) {
            System.out.println("Items:");

            for (GiftShopItem item : service.getAllItems()) {
                System.out.println(item);
            }

            System.out.println("\nSelect Option:\n1. Create Item\n2. Delete Item\n0. Quit");
            String input = scanner.nextLine().strip();

            switch (input) {
                case CREATE -> handleCreate();
                case DELETE -> handleDelete();
                case QUIT -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
