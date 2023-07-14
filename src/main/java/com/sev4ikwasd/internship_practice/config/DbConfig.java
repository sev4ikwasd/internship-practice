package com.sev4ikwasd.internship_practice.config;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
public class DbConfig {
    @NotNull
    private Integer serviceClassId;

    @NotNull
    private Integer groupingClassId;

    @NotNull
    @NotEmpty
    private List<@NotNull Integer> companyClassIdList;
}
