package com.jkn.model.entity;

import java.sql.Blob;

public record GiftShopItem(
        Long id,
        String name,
        int price,
        String description,
        Blob picture
) implements AbstractEntity {}

