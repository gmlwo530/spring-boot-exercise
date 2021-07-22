package com.example.demo.item;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDtoTest {

    @Test
    void itemDtoTest() {
        ItemDto itemDto = new ItemDto()
                .setCode("item_code")
                .setName("item_name")
                .setCategory(Categories.CATEGORY_A);

        assertEquals(itemDto.getCode(), "item_code");
        assertEquals(itemDto.getName(), "item_name");
        assertEquals(itemDto.getCategory(), Categories.CATEGORY_A);
    }

}