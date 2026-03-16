package com.zlrx.dbperftest.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table("users")
public record User(
        @Id Long id,
        String name,
        LocalDate birthDate,
        String description
) {}