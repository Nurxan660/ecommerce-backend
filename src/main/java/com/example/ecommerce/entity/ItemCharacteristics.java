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
    @Column(name="simple_char_index")
    private Long indexOfSimpleChar;
    @Column(name="detail_char_index")
    private Long indexOfDetailChar;


    public ItemCharacteristicKey getItemCharacteristicKey() {
        return itemCharacteristicKey;
    }

    public ItemCharacteristics(Item item) {
        this.item = item;
    }
    public ItemCharacteristics() {
        ;
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

    public Long getIndexOfSimpleChar() {
        return indexOfSimpleChar;
    }

    public void setIndexOfSimpleChar(Long indexOfSimpleChar) {
        this.indexOfSimpleChar = indexOfSimpleChar;
    }

    public Long getIndexOfDetailChar() {
        return indexOfDetailChar;
    }

    public void setIndexOfDetailChar(Long indexOfDetailChar) {
        this.indexOfDetailChar = indexOfDetailChar;
    }
}
