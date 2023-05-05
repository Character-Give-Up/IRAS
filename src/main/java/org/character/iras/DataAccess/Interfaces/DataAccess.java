package org.character.iras.DataAccess.Interfaces;

import org.character.iras.Application;
import org.springframework.jdbc.core.JdbcTemplate;

public interface DataAccess {
    default JdbcTemplate getJdbcTemplate(){
        return Application.template;
    }
}
