package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SubCharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subName;
    private String value;
    @ManyToOne
    @JoinColumn(name = "characteristics_id")
    @JsonIgnore
    private Characteristics characteristics;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
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
