package org.character.iras.DataAccess.Interfaces;

import java.util.List;

public interface PostDataAccess extends DataAccess {
    void addPost(String name);
    void removePost(String name);
    List<String> getPosts();
}
