package org.character.iras.DataAccess.Interfaces;

import java.util.List;

public interface ResumeDataAccess extends DataAccess{
    void putNewResumeData(int id, String path);
    List<Integer> getIds();
    int getMaximumId();
    String getURL();
}

