package org.character.iras.DataAccess.MySQLImplments;

import org.character.iras.DataAccess.Interfaces.ResumeDataAccess;
import org.character.iras.Entity.Resume;
import org.character.iras.Mappers.ResumeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MySQLResumeDataAccess implements ResumeDataAccess {
    @Override
    public void putNewResumeData(int id, String url) {
        JdbcTemplate template = getJdbcTemplate();
        template.update("INSERT into resume(id, url) VALUES(?, ?)", id, url);
    }

    @Override
    public void putNewResumeData(int id, Resume resume){
        JdbcTemplate template = getJdbcTemplate();
        template.update("INSERT INTO resume(id, `name`, age, HighestDegree, GraduateSchool, WorkingSeniority)" +
                " VALUES(?, ?, ?, ?, ?, ?)",
                id, resume.getName(), resume.getAge(),
                resume.getHighestDegree(),
                resume.getGraduateSchool(),
                resume.getWorkingSeniority());
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
        if(ids.size() == 0) return 0;
        ids.sort((o1, o2) -> o2-o1);
        return ids.get(0);
    }

    @Override
    public String getURL(int id) {
        return null;
    }

    @Override
    public List<Resume> getResumes(){
        return this.getJdbcTemplate().query("SELECT * FROM resume", new ResumeMapper());
    }
}
