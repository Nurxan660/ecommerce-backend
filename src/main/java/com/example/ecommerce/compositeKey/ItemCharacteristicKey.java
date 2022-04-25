package com.example.ecommerce.compositeKey;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemCharacteristicKey implements Serializable {

    private Long itmId;
    private Long subCharacteristicId;


    public ItemCharacteristicKey(Long itmId) {
        this.itmId = itmId;
    }
    public ItemCharacteristicKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCharacteristicKey that = (ItemCharacteristicKey) o;
        return Objects.equals(itmId, that.itmId) && Objects.equals(subCharacteristicId, that.subCharacteristicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itmId, subCharacteristicId);
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


}
