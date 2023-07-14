package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.Service;

import java.util.List;
import java.util.Optional;

public interface ServiceDao {
    Optional<Service> read(Integer id);

    List<Service> readAll();
}
