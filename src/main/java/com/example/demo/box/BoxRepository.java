package com.example.demo.box;

import org.springframework.data.repository.CrudRepository;

public interface BoxRepository extends CrudRepository<Box, Long> {
    Box findByCode(String boxCode);
}
