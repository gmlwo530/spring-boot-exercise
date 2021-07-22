package com.example.demo.box;

import com.example.demo.item.Categories;
import com.example.demo.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void model_build_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        final Box box = new Box()
                .setName("box_name");

        final Set<ConstraintViolation<Box>> itemViolations = validator.validate(box);

        assertEquals(1, itemViolations.size());

        box.setCode("box_code");

        assertEquals(box.getCode(), "box_code");
        assertEquals(box.getName(), "box_name");
        assertNull(box.getItems());

        Item item = new Item()
                .setCode("item_code")
                .setName("item_name")
                .setBox(box);

        HashSet<Item> items = new HashSet<>();
        items.add(item);

        box.setItems(items);

        assertEquals(box.getItems().size(), 1);
    }
}