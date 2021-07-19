package com.example.demo.box;

import com.example.demo.item.ItemDto;

public interface BoxService {
    BoxDto getBox(String boxCode);

    BoxDto addBox(BoxDto boxDto);

    BoxDto updateBox(BoxDto boxDto, ItemDto itemDto);
}
