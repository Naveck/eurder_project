package com.switchfully.eurder.item.service.mappers;

import com.switchfully.eurder.item.domain.models.Item;
import com.switchfully.eurder.item.service.dtos.ItemDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public Item toDomain(ItemDTO itemDTO){
        return new Item(itemDTO.getName(),
                itemDTO.getDescription(),
                itemDTO.getPrice(),
                itemDTO.getStockAmount());
    }
}
