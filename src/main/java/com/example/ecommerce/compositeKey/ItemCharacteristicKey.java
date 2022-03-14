package com.example.ecommerce.compositeKey;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemCharacteristicKey implements Serializable {

    private Long itemId;
    private Long characteristicId;
    @Column(name="simple_char_index")
    private Long indexOfSimpleChar;
    @Column(name="detail_char_index")
    private Long indexOfDetailChar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCharacteristicKey that = (ItemCharacteristicKey) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(characteristicId, that.characteristicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, characteristicId);
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(Long characteristicId) {
        this.characteristicId = characteristicId;
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
