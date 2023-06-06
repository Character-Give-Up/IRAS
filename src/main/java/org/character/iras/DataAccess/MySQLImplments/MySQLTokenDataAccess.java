package org.character.iras.DataAccess.MySQLImplments;

import jakarta.annotation.Nullable;
import org.character.iras.DataAccess.Interfaces.TokenDataAccess;
import org.character.iras.Entity.Token;
import org.character.iras.Mappers.TokenMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

@Repository
public class MySQLTokenDataAccess implements TokenDataAccess {

    public MySQLTokenDataAccess() {
    }

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

    @Override
    public void addToken(String value) {
        JdbcTemplate template = getJdbcTemplate();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        template.update("insert into token(token_value, expired_time) values (?, ?)", value, calendar.getTime());
    }
}
