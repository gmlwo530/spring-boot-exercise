package com.example.demo.box;

import com.example.demo.exception.DemoException;
import com.example.demo.exception.EntityType;
import com.example.demo.exception.ExceptionType;
import com.example.demo.item.Item;
import com.example.demo.item.ItemDto;
import com.example.demo.item.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

import static com.example.demo.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.example.demo.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class BoxServiceImpl implements BoxService {

    @Autowired
    private BoxRepository boxRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public BoxDto getBox(String boxCode) {
        Optional<Box> box = Optional.ofNullable(boxRepository.findByCode(boxCode));
        if (box.isPresent()) {
            return modelMapper.map(box.get(), BoxDto.class);
        }
        throw exception(ENTITY_NOT_FOUND, boxCode);
    }

    @Override
    public BoxDto addBox(BoxDto boxDto) {
        Optional<Box> box = Optional.ofNullable(boxRepository.findByCode(boxDto.getCode()));

        if (box.isEmpty()) {
            Box boxModel = new Box()
                    .setCode(boxDto.getCode());
            boxRepository.save(boxModel);
            return modelMapper.map(boxModel, BoxDto.class);
        }

        throw exception(DUPLICATE_ENTITY, boxDto.getCode());
    }

    @Override
    public BoxDto updateBox(BoxDto boxDto, ItemDto itemDto) {
        Box box = boxRepository.findByCode(boxDto.getCode());

        if (box != null) {
            if (itemDto != null) {
                Optional<Item> item = Optional.ofNullable(itemRepository.findByCodeAndBox(itemDto.getCode(), box));
                if (item.isEmpty()) {
                    Item itemModel = new Item()
                            .setBox(box)
                            .setCode(itemDto.getCode())
                            .setName(itemDto.getName())
                            .setCategory(itemDto.getCategory());
                    itemRepository.save(itemModel);
                    if (box.getItems() == null) {
                        box.setItems(new HashSet<>());
                    }
                    box.getItems().add(itemModel);
                    return modelMapper.map(boxRepository.save(box), BoxDto.class);
                }
                throw exceptionWithId(EntityType.ITEM, DUPLICATE_ENTITY, 2, itemDto.getCode(), boxDto.getCode());
            } else {
                box.setName(boxDto.getName());
                return modelMapper.map(boxRepository.save(box), BoxDto.class);
            }
        }
        throw exceptionWithId(EntityType.BOX, ENTITY_NOT_FOUND, 2, boxDto.getCode());
    }

    private RuntimeException exception(ExceptionType exceptionType, String... args) {
        return DemoException.throwException(EntityType.BOX, exceptionType, args);
    }

    private RuntimeException exceptionWithId(EntityType entityType, ExceptionType exceptionType, Integer id, String... args) {
        return DemoException.throwExceptionWithId(entityType, exceptionType, id, args);
    }
}
