package org.character.iras.DataAccess.Interfaces;

import jakarta.annotation.Nullable;
import org.character.iras.Entity.User;

import java.util.List;
import java.util.function.Predicate;

public interface UserDataAccess extends DataAccess{

    @Nullable
    User getUserByUsername(String username);
    @Nullable
    User getUserByEmail(String email);
    @Nullable
    User geiUserByResumeId(int resumeId);
    @Nullable
    List<User> getUser(String condition) throws Exception;

}
