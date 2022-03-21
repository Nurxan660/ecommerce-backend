package com.example.ecommerce.entity;

import com.example.ecommerce.compositeKey.CharacteristicsCategoryKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubCharacteristicsCategory {

    @EmbeddedId
    @JsonIgnore
    private CharacteristicsCategoryKey characteristicsCategoryKey;
    @ManyToOne
    @JoinColumn(name="category_id")
    @MapsId("categoryId")
    private Category category;
    @ManyToOne
    @JoinColumn(name="subCharacteristics_id")
    @MapsId("subCharacteristicsId")
    private SubCharacteristics subCharacteristics;


    public CharacteristicsCategoryKey getCharacteristicsCategoryKey() {
        return characteristicsCategoryKey;
    }

    public void setCharacteristicsCategoryKey(CharacteristicsCategoryKey characteristicsCategoryKey) {
        this.characteristicsCategoryKey = characteristicsCategoryKey;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCharacteristics getSubCharacteristics() {
        return subCharacteristics;
    }

    public void setSubCharacteristics(SubCharacteristics subCharacteristics) {
        this.subCharacteristics = subCharacteristics;
    }
}
