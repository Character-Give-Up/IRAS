package org.character.iras.Mappers;

import org.character.iras.Entity.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResumeMapper implements RowMapper<Resume> {
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
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String url = rs.getString("url");
        String keywords = rs.getString("keywords");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String highestDegree = rs.getString("HighestDegree");
        String graduateSchool = rs.getString("GraduateSchool");
        String workingSeniority = rs.getString("WorkingSeniority");
        String post = rs.getString("post");
        Resume resume = new Resume(id, url);
        if(keywords != null){
            String[] split = keywords.split(", ");
            for (String key : split) {
                resume.addKeyword(key);
            }
        }

        resume.setName(name);
        resume.setAge(age);
        resume.setHighestDegree(highestDegree);
        resume.setGraduateSchool(graduateSchool);
        resume.setWorkingSeniority(workingSeniority);
        resume.setPost(post);

        return resume;
    }
}
