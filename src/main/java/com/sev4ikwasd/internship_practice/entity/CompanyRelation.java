package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyRelation {
    private int id;
    private String fullName;
    private String name;
    private String shortName;
    private Company company;
}
