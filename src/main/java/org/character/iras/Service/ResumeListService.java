package org.character.iras.Service;

import org.character.iras.DataAccess.Interfaces.ResumeDataAccess;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLResumeDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Entity.Resume;
import org.character.iras.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeListService {

    private UserDataAccess userDataAccess;
    private ResumeDataAccess resumeDataAccess;

    @Autowired
    public void setUserDataAccess(MySQLUserDataAccess access){
        this.userDataAccess = access;
    }

    @Autowired
    public void setResumeDataAccess(MySQLResumeDataAccess access){
        this.resumeDataAccess = access;
    }

    public List<Resume> getResumes(){
        List<User> users = userDataAccess.getUsers();
        List<Resume> resumes = resumeDataAccess.getResumes();
        List<Integer> ids = new ArrayList<>(users.size());
        for (User user : users) {
            ids.add(user.getResumeId());
        }
        resumes.removeIf(resume -> !ids.contains(resume.getId()));
        return resumes;

    }
}
