package com.example.ecommerce.dto;

import com.example.ecommerce.entity.SubCharacteristicsCategory;

import java.util.List;

public class FilterCharacteristicsResponse {
    private String name;
    private List<SubCharacteristicsCategory> values;

    public FilterCharacteristicsResponse(String name, List<SubCharacteristicsCategory> values) {
        this.name = name;
        this.values = values;
    }

    public FilterCharacteristicsResponse() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCharacteristicsCategory> getValues() {
        return values;
    }

    public void setValues(List<SubCharacteristicsCategory> values) {
        this.values = values;
    }
}
