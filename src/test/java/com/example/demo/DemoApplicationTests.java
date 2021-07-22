package com.example.demo;

import com.example.demo.box.BoxRepository;
import com.example.demo.box.BoxServiceImpl;
import com.example.demo.item.ItemRepository;
import com.example.demo.item.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private BoxRepository boxRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BoxServiceImpl boxService;

	@Autowired
	private ItemServiceImpl itemService;

	@Test
	void contextLoads() {
		assertThat(boxRepository).isNotNull();
		assertThat(itemRepository).isNotNull();
		assertThat(boxService).isNotNull();
		assertThat(itemService).isNotNull();
	}

}
