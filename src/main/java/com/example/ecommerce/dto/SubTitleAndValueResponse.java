package com.example.ecommerce.dto;

public class SubTitleAndValueResponse {

    private Long subId;
    private String subTitle;
    private String value;

    public SubTitleAndValueResponse(Long subId, String subTitle, String value) {
        this.subId = subId;
        this.subTitle = subTitle;
        this.value = value;
    }

    public SubTitleAndValueResponse() {

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

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }
}
