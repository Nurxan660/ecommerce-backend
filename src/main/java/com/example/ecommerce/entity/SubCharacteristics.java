package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubCharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subId;
    private String subTitle;
    private String value;
    @ManyToOne()
    @JoinColumn(name = "characteristics_id")
    @JsonIgnore
    private Characteristics characteristics;


    public Long getId() {
        return subId;
    }

    public void setId(Long id) {
        this.subId = id;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Characteristics characteristics) {
        this.characteristics = characteristics;
    }

}
