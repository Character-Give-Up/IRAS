package org.character.iras.Service;

import org.character.iras.DataAccess.Interfaces.ResumeDataAccess;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLResumeDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditResumeService {

    private ResumeDataAccess resumeDataAccess;
    private UserDataAccess userDataAccess;

    @Autowired
    private void setResumeDataAccess(MySQLResumeDataAccess access){
        this.resumeDataAccess = access;
    }

    @Autowired
    private void setUserDataAccess(MySQLUserDataAccess access){
        this.userDataAccess = access;
    }

    public void addNewResumeData(String username, Resume resume){
        int maximumId = resumeDataAccess.getMaximumId() + 1;
        resumeDataAccess.putNewResumeData(maximumId, resume);
        userDataAccess.setUserResumeId(username, maximumId);
    }
}
