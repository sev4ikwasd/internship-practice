package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.Grouping;

import java.util.List;
import java.util.Optional;

public interface GroupingDao {
    Optional<Grouping> read(Integer id);

    List<Grouping> readAllForService(Integer id);
}
