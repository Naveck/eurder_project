package com.switchfully.eurder.item.service;

import com.switchfully.eurder.item.domain.repositories.ItemRepository;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import com.switchfully.eurder.item.service.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Autowired
    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public void addItem(ItemDTO newItem){
        itemRepository.addItem(itemMapper.toDomain(newItem));
    }
}
