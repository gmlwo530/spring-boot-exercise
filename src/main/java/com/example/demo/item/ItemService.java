package com.example.demo.item;


import java.util.List;


public interface ItemService {
    ItemDto getItemByCode(String itemCode);

    ItemDto getItemById(Long itemId);

    List<ItemDto> getItemsByBoxId(Long boxId);
}
