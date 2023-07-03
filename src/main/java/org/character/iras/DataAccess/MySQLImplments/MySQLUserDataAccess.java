package org.character.iras.DataAccess.MySQLImplments;

import jakarta.annotation.Nullable;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.Entity.User;
import org.character.iras.Mappers.UserMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
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
    public User getUserByResumeId(int resumeId) {
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

    /**
     * 更改某一个用户的最后登录时间
     * @param username 用户名
     * @param date 最后登录时间
     */
    @Override
    public void setUserLastLoginTime(String username, Date date){
        JdbcTemplate template = getJdbcTemplate();
        template.update("UPDATE `user` SET last_login=? where username=?", date, username);
    }

    /**
     * 更改某一个用户的最后使用token
     * @param username 用户名
     * @param token 最后使用token
     */
    @Override
    public void setUserLastToken(String username, String token){
        JdbcTemplate template = getJdbcTemplate();
        template.update("UPDATE `user` SET last_token=? where username=?", token, username);
    }

    @Override
    public List<User> getUsers(){
        JdbcTemplate template = getJdbcTemplate();
        return template.query("SELECT * FROM user", new UserMapper());
    }
}
