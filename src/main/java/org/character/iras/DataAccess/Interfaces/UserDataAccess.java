package org.character.iras.DataAccess.Interfaces;

import jakarta.annotation.Nullable;
import org.character.iras.Entity.User;

import java.util.Date;
import java.util.List;

public interface UserDataAccess extends DataAccess{

    @Nullable
    User getUserByUsername(String username);
    @Nullable
    User getUserByEmail(String email);

    @Nullable
    User getUserByResumeId(int resumeId);

    @Nullable
    List<User> getUser(String condition) throws Exception;

    void setUserLastLoginTime(String username, Date date);

    void setUserLastToken(String username, String token);
}
