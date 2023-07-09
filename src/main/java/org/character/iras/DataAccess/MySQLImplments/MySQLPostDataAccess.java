package org.character.iras.DataAccess.MySQLImplments;

import org.character.iras.DataAccess.Interfaces.PostDataAccess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
