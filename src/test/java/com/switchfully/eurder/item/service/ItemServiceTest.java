package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.models.Item;
import com.switchfully.eurder.item.domain.repositories.ItemRepository;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import com.switchfully.eurder.item.service.mappers.ItemMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @Mock
    ItemRepository itemRepositoryMock;
    @Mock
    ItemMapper itemMapperMock;
    @InjectMocks
    ItemService itemService;

    public static final Item NEW_ITEM = new Item("itemName","someDescription",100.0,1);
    public static final ItemDTO NEW_ITEMDTO = new ItemDTO("itemName","someDescription",100.0,1);

    @Test
    void addItem_confirmItemHasBeenAddedToList(){
        itemService.addItem(NEW_ITEMDTO);

        Mockito.verify(itemRepositoryMock).addItem(itemMapperMock.toDomain(NEW_ITEMDTO));
    }
}