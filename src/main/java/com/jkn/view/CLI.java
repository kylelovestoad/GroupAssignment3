package com.jkn.view;

import com.jkn.controller.GiftShopItemService;
import com.jkn.model.entity.GiftShopItem;

import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static final String CREATE = "1";
    private static final String READ = "2";
    private static final String UPDATE = "3";
    private static final String DELETE = "4";
    private static final String LIST = "5";
    private static final String QUIT = "0";

    private static final String NAME = "1";
    private static final String PRICE = "2";
    private static final String DESCRIPTION = "3";
    private static final String PICTURE = "4";

    GiftShopItemService service = new GiftShopItemService();
    Scanner scanner = new Scanner(System.in);

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

    private File getFileInput(String prompt) {
        System.out.println(prompt);

        File file = new File(scanner.nextLine());
        while (!(file.exists() && file.isFile())) {
            System.out.println("File not found");
            file = new File(scanner.nextLine());
        }

        return file;
    }

    private GiftShopItem getItemInput() throws Exception {
        List<GiftShopItem> items = service.getAllItems();
        displayItems(items);

        while(true) {
            int itemId = getIntInput("Enter Item Number: ");

            for (GiftShopItem item: items) {
                if (item.getID() == itemId) {
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

    private void handleRead() {
        try {
            int itemId = getIntInput("Enter Item Id: ");

            GiftShopItem item = service.readGiftShopItem(itemId);

            if (item == null) {
                System.out.println("No item with id " + itemId);
            } else {
                System.out.println(item);
            }
        } catch (Exception e) {
            System.out.println("Failed to create gift shop item");
        }
    }

    private void handleDelete() {
        try {
            List<GiftShopItem> items = service.getAllItems();
            displayItems(items);

            int itemId = getIntInput("Enter number of item to delete");

            service.deleteGiftShopItem(itemId);
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

    private void handleUpdatePicture(GiftShopItem item) throws Exception {
        File file = getFileInput("New Image file: ");

        service.updateGiftShopItemPicture(item, Files.readAllBytes(file.toPath()));
    }

    private void displayItems(List<GiftShopItem> items) throws Exception {

        items.sort(Comparator.comparingLong(GiftShopItem::getID));

        for (GiftShopItem item : items) {
            System.out.println(item);
        }
    }

    private void handleUpdate() {
        try {
            GiftShopItem item  = getItemInput();

            String field = getStringInput("Enter field to update:\n1. Name\n2. Price\n3. Description\n4. Picture");

            switch (field) {
                case NAME -> handleUpdateName(item);
                case PRICE -> handleUpdatePrice(item);
                case DESCRIPTION -> handleUpdateDescription(item);
                case PICTURE -> handleUpdatePicture(item);
                default -> System.out.println("Invalid input");
            }

        } catch (Exception e) {
            System.out.println("Failed to update gift shop item");
        }
    }

    private void handleList() {
        try {
            displayItems(service.getAllItems());
        } catch (Exception e) {
            System.out.println("Failed to display gift shop items");
        }
    }

    public void run() throws Exception {

        while (true) {

            System.out.println("\nSelect Option:\n1. Create Item\n2. Display Item\n3. Update Item\n4. Delete Item\n5. List All Items\n0. Quit");
            String input = scanner.nextLine().strip();

            switch (input) {
                case CREATE -> handleCreate();
                case READ -> handleRead();
                case DELETE -> handleDelete();
                case UPDATE -> handleUpdate();
                case LIST -> handleList();
                case QUIT -> {
                    return;
                }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
