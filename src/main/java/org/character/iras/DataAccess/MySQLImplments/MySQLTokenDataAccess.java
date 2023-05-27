package org.character.iras.DataAccess.MySQLImplments;

import jakarta.annotation.Nullable;
import org.character.iras.DataAccess.Interfaces.TokenDataAccess;
import org.character.iras.Entity.Token;
import org.character.iras.Mappers.TokenMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySQLTokenDataAccess implements TokenDataAccess {
    @Override
    @Nullable
    public Token getTokenByValue(String value) {
        JdbcTemplate template = getJdbcTemplate();
        try {
            return template.queryForObject("select * from token where token_value=?", new TokenMappers(), value);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
