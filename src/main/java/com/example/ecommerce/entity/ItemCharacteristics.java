package com.example.ecommerce.entity;

import com.example.ecommerce.compositeKey.ItemCharacteristicKey;

import javax.persistence.*;

@Entity
public class ItemCharacteristics {

    @EmbeddedId
    private ItemCharacteristicKey itemCharacteristicKey;

    @ManyToOne
    @JoinColumn(name="itm_id")
    @MapsId("itmId")
    private Item item;
    @ManyToOne
    @JoinColumn(name="sub_characteristic_id")
    @MapsId("subCharacteristicId")
    private SubCharacteristics subCharacteristics;


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

    public SubCharacteristics getSubCharacteristics() {
        return subCharacteristics;
    }

    public void setSubCharacteristics(SubCharacteristics subCharacteristics) {
        this.subCharacteristics = subCharacteristics;
    }
}
