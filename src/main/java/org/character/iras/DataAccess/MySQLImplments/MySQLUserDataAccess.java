package org.character.iras.DataAccess.MySQLImplments;

import jakarta.annotation.Nullable;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.Entity.User;
import org.character.iras.Mappers.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MySQLUserDataAccess implements UserDataAccess {
    @Override
    @Nullable
    public User getUserByUsername(String username) {
        JdbcTemplate template = getJdbcTemplate();
        try {
            return template.queryForObject("select * from user where username=?", new UserMapper(), username);
        }catch (EmptyResultDataAccessException e){
            return null;
        }

    }

    @Nullable
    @Override
    public User getUserByEmail(String email) {
        JdbcTemplate template = getJdbcTemplate();
        try {
            return template.queryForObject("select * from user where email=?", new UserMapper(), email);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Nullable
    @Override
    public User geiUserByResumeId(int resumeId) {
        JdbcTemplate template = getJdbcTemplate();
        try {
            return template.queryForObject("select * from user where resume_id=?", new UserMapper(), resumeId);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Nullable
    @Override
    public List<User> getUser(String condition) throws Exception {
        JdbcTemplate template = getJdbcTemplate();
        try {
            return template.query("select * from user where ?", new UserMapper(), condition);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
