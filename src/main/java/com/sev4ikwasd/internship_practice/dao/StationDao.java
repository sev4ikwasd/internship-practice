package com.sev4ikwasd.internship_practice.dao;

import com.sev4ikwasd.internship_practice.entity.Station;

import java.util.Optional;

public interface StationDao {
    Optional<Station> read(Integer id);
}
