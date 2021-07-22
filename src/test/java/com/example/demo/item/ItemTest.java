package com.example.demo.item;

import com.example.demo.box.Box;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {


    @Test
    void model_build_test() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        final Item item = new Item()
                .setCode("item_code")
                .setName("item_name")
                .setCategory(Categories.CATEGORY_A);

        final Set<ConstraintViolation<Item>> itemViolations = validator.validate(item);

        assertEquals(1, itemViolations.size());

        Box box = new Box().setName("box_name").setCode("box_code");
        item.setBox(box);

        assertEquals(item.getCode(), "item_code");
        assertEquals(item.getName(), "item_name");
        assertEquals(item.getCategory(), Categories.CATEGORY_A);
        assertEquals(item.getBox(), box);
    }

}