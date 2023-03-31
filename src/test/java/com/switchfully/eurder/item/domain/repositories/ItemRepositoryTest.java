package com.switchfully.eurder.item.domain.repositories;

import com.switchfully.eurder.item.domain.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository;
    private Item item;

    @BeforeEach
    void setupTest(){
        //Given
        itemRepository = new ItemRepository();
        item = new Item("itemName","someDescription",100.0,1);
        //When
        itemRepository.addItem(item);
    }

    @Test
    void addItem_confirmItemHasBeenAddedToList(){
        //Then
        assertTrue(itemRepository.getAllItems().contains(item));
    }

}