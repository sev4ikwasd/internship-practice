package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Service {
    private int id;
    private String fullName;
    private String name;
    private String shortName;
    private List<Grouping> groupingList;
}
