package com.piggybank.piggybank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wealth")
public class WealthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<String, BigDecimal> wealthMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, BigDecimal> getWealthMap() {
        return wealthMap;
    }

    public void setWealthMap(Map<String, BigDecimal> wealthMap) {
        this.wealthMap = wealthMap;
    }

    public WealthEntity(Long userId,
                        Map<String, BigDecimal> wealthMap) {
    }
}
