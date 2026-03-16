package com.zlrx.dbperftest.user;

import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

public class CustomUserRepositoryImpl implements CustomUserRepository {

    private JdbcClient jdbcClient;

    public CustomUserRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    //@MeterTag
    //
    // @Observed(name = "spring.data.repository.custom", contextualName = "user-find-all")
    public List<User> loadAllUsers() {
        var res = jdbcClient.sql("SELECT id, name, birth_date, description FROM users where id = 1")
                .query(User.class)
                .list();

        return res;
    }
}
