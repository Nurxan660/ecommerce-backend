package com.example.ecommerce.entity;

import com.example.ecommerce.compositeKey.ItemCharacteristicKey;

import javax.persistence.*;

@Entity
public class ItemCharacteristics {

    @EmbeddedId
    private ItemCharacteristicKey itemCharacteristicKey;

    @ManyToOne
    @JoinColumn(name="item_id")
    @MapsId("itemId")
    private Item item;
    @ManyToOne
    @JoinColumn(name="characteristic_id")
    @MapsId("characteristicId")
    private Characteristics characteristics;


    public ItemCharacteristicKey getItemCharacteristicKey() {
        return itemCharacteristicKey;
    }

    public void setItemCharacteristicKey(ItemCharacteristicKey itemCharacteristicKey) {
        this.itemCharacteristicKey = itemCharacteristicKey;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }



}
