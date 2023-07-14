package com.sev4ikwasd.internship_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Grouping {
    private int id;
    private String fullName;
    private String name;
    private String shortName;
}
