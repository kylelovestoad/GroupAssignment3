package com.jkn;

import java.sql.Blob;

public record GiftShopItem(
        long id,
        String name,
        int price,
        String description,
        Blob picture
) {}

