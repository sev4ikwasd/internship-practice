package com.sev4ikwasd.internship_practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GroupingNotFoundException extends RuntimeException {
    public GroupingNotFoundException(Integer id) {
        super("Could not find grouping with id " + id);
    }
}
