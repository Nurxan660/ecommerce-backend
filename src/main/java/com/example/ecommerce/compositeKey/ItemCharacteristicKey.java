package com.example.ecommerce.compositeKey;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemCharacteristicKey implements Serializable {

    private Long itmId;
    private Long subCharacteristicId;
    @Column(name="simple_char_index")
    private Long indexOfSimpleChar;
    @Column(name="detail_char_index")
    private Long indexOfDetailChar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCharacteristicKey that = (ItemCharacteristicKey) o;
        return Objects.equals(itmId, that.itmId) && Objects.equals(subCharacteristicId, that.subCharacteristicId) && Objects.equals(indexOfSimpleChar, that.indexOfSimpleChar) && Objects.equals(indexOfDetailChar, that.indexOfDetailChar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itmId, subCharacteristicId, indexOfSimpleChar, indexOfDetailChar);
    }

    public Long getItmId() {
        return itmId;
    }

    public void setItmId(Long itmId) {
        this.itmId = itmId;
    }

    public Long getSubCharacteristicId() {
        return subCharacteristicId;
    }

    public void setSubCharacteristicId(Long subCharacteristicId) {
        this.subCharacteristicId = subCharacteristicId;
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
