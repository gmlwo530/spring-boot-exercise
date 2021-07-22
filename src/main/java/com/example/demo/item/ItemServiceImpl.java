package com.example.demo.item;

import com.example.demo.exception.DemoException;
import com.example.demo.exception.EntityType;
import com.example.demo.exception.ExceptionType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemDto getItemByCode(String itemCode) {
        Optional<Item> item = Optional.ofNullable(itemRepository.findByCode(itemCode));
        if (item.isPresent()) {
            return modelMapper.map(item.get(), ItemDto.class);
        }
        throw exception(ExceptionType.ENTITY_NOT_FOUND, itemCode);
    }

    @Override
    public ItemDto getItemById(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            return modelMapper.map(item.get(), ItemDto.class);
        }
        throw exception(ExceptionType.ENTITY_NOT_FOUND, String.valueOf(itemId));
    }

    @Override
    public List<ItemDto> getItemsByBoxId(Long boxId) {
        List<Item> items = itemRepository.findByBoxId(boxId);
        if (!items.isEmpty()) {
            return items.stream().map(item -> modelMapper.map(item, ItemDto.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private RuntimeException exception(ExceptionType exceptionType, String... args) {
        return DemoException.throwException(EntityType.ITEM, exceptionType, args);
    }
}
