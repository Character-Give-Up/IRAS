package org.character.iras.DataAccess.MySQLImplments;

import org.character.iras.DataAccess.Interfaces.PostDataAccess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MySQLPostDataAccess implements PostDataAccess {
    @Override
    public void addPost(String name) {
        JdbcTemplate template = getJdbcTemplate();
        List<Map<String, Object>> maps = template.queryForList("select * from post");
        for (Map<String, Object> map : maps) {
            if (map.get("post_name").equals(name)) {
                return;
            }
        }
        template.update("INSERT INTO post(post_name) VALUES(?)", name);
    }

    @Override
    public void removePost(String name) {
        JdbcTemplate template = getJdbcTemplate();
        template.update("delete from post as p where p.post_name=?", name);
    }

    @Override
    public List<String> getPosts() {
        JdbcTemplate template = getJdbcTemplate();
        List<Map<String, Object>> maps = template.queryForList("select * from posts");
        List<String> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Object postName = map.get("post_name");
            result.add((String) postName);
        }
        return result;
    }
}
