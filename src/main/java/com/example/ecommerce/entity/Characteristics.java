package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Characteristics {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String name;
        @OneToMany(mappedBy = "characteristics")
        private List<SubCharacteristics> subCharacteristics;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }

    public List<SubCharacteristics> getSubCharacteristics() {
        return subCharacteristics;
    }

    public void setSubCharacteristics(List<SubCharacteristics> subCharacteristics) {
        this.subCharacteristics = subCharacteristics;
    }
}


