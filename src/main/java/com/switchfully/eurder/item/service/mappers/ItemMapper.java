package com.switchfully.eurder.item.service.mappers;

import com.switchfully.eurder.item.domain.models.Item;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemMapper {

    public Item toDomain(ItemDTO itemDTO){
        return new Item(itemDTO.getName(),
                itemDTO.getDescription(),
                itemDTO.getPrice(),
                itemDTO.getStockAmount());
    }

    public ItemDTO toDTO(Item item){
        return new ItemDTO(item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStockAmount());
    }

    public List<ItemDTO> toDTO(List<Item> itemList){
       return itemList.stream()
                .map(item -> toDTO(item))
                .toList();
    }
}
