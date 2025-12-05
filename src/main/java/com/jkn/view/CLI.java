package com.jkn.view;

import com.jkn.controller.GiftShopItemService;
import com.jkn.model.entity.GiftShopItem;

import javax.sql.rowset.serial.SerialBlob;
import java.util.Comparator;
import java.util.List;
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
    List<GiftShopItem> items;

    private String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private float getFloatInput(String prompt) {

        System.out.println(prompt);

        while (true) {
            if (scanner.hasNextFloat()) {
                float f = scanner.nextFloat();
                scanner.nextLine();
                return f;
            } else {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    private int getIntInput(String prompt) {

        System.out.println(prompt);

        while (true) {
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } else {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        }
    }

    private GiftShopItem getItemInput() {
        while(true) {
            int item_id = getIntInput("Enter Item Number: ");

            for (GiftShopItem item: items) {
                if (item.getID() == item_id) {
                    return item;
                }
            }

            System.out.println("Invalid item");
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

    private void handleUpdateName(GiftShopItem item) throws Exception {
        String name = getStringInput("New Name: ");

        service.updateGiftShopItemName(item, name);
    }

    private void handleUpdatePrice(GiftShopItem item) throws Exception {
        int price = (int) (getFloatInput("New Price: ") * 100);

        service.updateGiftShopItemPrice(item, price);
    }

    private void handleUpdateDescription(GiftShopItem item) throws Exception {
        String description = getStringInput("New Description: ");

        service.updateGiftShopItemDescription(item, description);
    }

    private void handleUpdate() {
        try {
            GiftShopItem item  = getItemInput();

            String field = getStringInput("Enter field to update:\n1. Name\n2. Price\n3. Description");

            switch (field) {
                case NAME -> handleUpdateName(item);
                case PRICE -> handleUpdatePrice(item);
                case DESCRIPTION -> handleUpdateDescription(item);
                default -> System.out.println("Invalid input");
            }

        } catch (Exception e) {
            System.out.println("Failed to update gift shop item");
        }
    }

    public void run() throws Exception {

        while (true) {
            items = service.getAllItems();
            items.sort(Comparator.comparingLong(GiftShopItem::getID));

            for (GiftShopItem item : items) {
                System.out.println(item);
            }

            System.out.println("\nSelect Option:\n1. Create Item\n2. Delete Item\n3. Update Item\n0. Quit");
            String input = scanner.nextLine().strip();

            switch (input) {
                case CREATE -> handleCreate();
                case DELETE -> handleDelete();
                case UPDATE -> handleUpdate();
                case QUIT -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
