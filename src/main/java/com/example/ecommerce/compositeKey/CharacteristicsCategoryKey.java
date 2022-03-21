package com.example.ecommerce.compositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CharacteristicsCategoryKey implements Serializable {

    private Long categoryId;
    private Long subCharacteristicsId;
    private Integer index;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubCharacteristicsId() {
        return subCharacteristicsId;
    }

    public void setSubCharacteristicsId(Long subCharacteristicsId) {
        this.subCharacteristicsId = subCharacteristicsId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacteristicsCategoryKey that = (CharacteristicsCategoryKey) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(subCharacteristicsId, that.subCharacteristicsId) && Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, subCharacteristicsId, index);
    }
}
