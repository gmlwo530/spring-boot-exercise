package com.example.demo.item;

import com.example.demo.box.Box;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByCode(String itemCode);

    Item findByCodeAndBox(String itemCode, Box box);

    List<Item> findByBoxId(Long boxId);
}
