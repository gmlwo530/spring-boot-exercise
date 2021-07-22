package com.example.demo.box;

import com.example.demo.item.Categories;
import com.example.demo.item.ItemDto;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class BoxDtoTest {

    @Test
    void dtoTest(){
        ItemDto itemDto = new ItemDto()
                .setName("item_name")
                .setCode("item_code")
                .setCategory(Categories.CATEGORY_A);

        HashSet<ItemDto> itemDtoHashSet = new HashSet<>();
        itemDtoHashSet.add(itemDto);

        BoxDto boxDto = new BoxDto()
                .setCode("box_code")
                .setName("box_name")
                .setItems(itemDtoHashSet);

        assertEquals(boxDto.getName(), "box_name");
        assertEquals(boxDto.getCode(), "box_code");
        assertEquals(boxDto.getItems(), itemDtoHashSet);
    }

}