package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Characteristics {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long propertiesId;
        private String propertiesName;



        public Long getId() {
            return propertiesId;
        }

        public void setId(Long id) {
            this.propertiesId = id;
        }

    public Long getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(Long propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getPropertiesName() {
        return propertiesName;
    }

    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }


}


