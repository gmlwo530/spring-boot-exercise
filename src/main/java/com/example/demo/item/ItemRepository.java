package com.example.demo.item;

import com.example.demo.box.Box;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByCode(String itemCode);

    Item findByCodeAndBox(String itemCode, Box box);
}
