package com.example.ecommerce.dto;

import com.example.ecommerce.entity.SubCharacteristics;

import java.util.List;

public class CharacteristicsResponse {
    private Long propertiesId;
    private String propertiesName;
    private List<SubTitleAndValueResponse> subTitleAndValueResponses;

    public CharacteristicsResponse(Long propertiesId, String propertiesName, List<SubTitleAndValueResponse> subTitleAndValueResponses) {
        this.propertiesId = propertiesId;
        this.propertiesName = propertiesName;
        this.subTitleAndValueResponses = subTitleAndValueResponses;
    }

    public CharacteristicsResponse() {

    }



    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }

    public List<SubTitleAndValueResponse> getSubTitleAndValueResponses() {
        return subTitleAndValueResponses;
    }

    public void setSubTitleAndValueResponses(List<SubTitleAndValueResponse> subTitleAndValueResponses) {
        this.subTitleAndValueResponses = subTitleAndValueResponses;
    }

    public Long getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(Long propertiesId) {
        this.propertiesId = propertiesId;
    }
}
