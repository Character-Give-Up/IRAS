package org.character.iras.DataAccess.MySQLImplments;

import org.character.iras.DataAccess.Interfaces.ResumeDataAccess;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLResumeDataAccess implements ResumeDataAccess {
    @Override
    public void putNewResumeData(int id, String path) {
        JdbcTemplate template = getJdbcTemplate();
        template.update("INSERT into resume(id, path) VALUES(?, '?')", id, path);
    }

    @Override
    public List<Integer> getIds() {
        JdbcTemplate template = getJdbcTemplate();
        List<Map<String, Object>> maps = template.queryForList("SELECT id FROM resume");
        List<Integer> result = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            int id = (int) map.get("id");
            result.add(id);
        }
        return result;
    }

    @Override
    public int getMaximumId() {
        List<Integer> ids = getIds();
        ids.sort((o1, o2) -> o2-o1);
        return ids.get(0);
    }
}
