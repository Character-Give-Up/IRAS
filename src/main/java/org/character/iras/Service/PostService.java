package org.character.iras.Service;

import org.character.iras.DataAccess.Interfaces.PostDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLPostDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private PostDataAccess postDataAccess;

    @Autowired
    private void setPostDataAccess(MySQLPostDataAccess access){
        this.postDataAccess = access;
    }

    public void addPost(String name){
        postDataAccess.addPost(name);
    }
}
