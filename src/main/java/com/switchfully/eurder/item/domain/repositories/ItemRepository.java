package com.switchfully.eurder.item.domain.repositories;

import com.switchfully.eurder.item.domain.models.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepository {
    private final List<Item> itemDatabase = new ArrayList<>();

    public void addItem (Item newItem){
        itemDatabase.add(newItem);
    }

    public List<Item> getAllItems(){
        return List.copyOf(itemDatabase);
    }
}
