package org.character.iras.Mappers;

import org.character.iras.Entity.Token;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TokenMappers implements RowMapper<Token> {
    /**
     * Implementations must implement this method to map each row of data in the
     * {@code ResultSet}. This method should not call {@code next()} on the
     * {@code ResultSet}; it is only supposed to map values of the current row.
     *
     * @param rs     the {@code ResultSet} to map (pre-initialized for the current row)
     * @param rowNum the number of the current row
     * @return the result object for the current row (may be {@code null})
     * @throws SQLException if an SQLException is encountered while getting
     *                      column values (that is, there's no need to catch SQLException)
     */
    @Override
    public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
        String tokenValue = rs.getString("token_value");
        Timestamp date = rs.getTimestamp("expired_time");
        return new Token(tokenValue, date);
    }
}
