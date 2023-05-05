package org.character.iras.Service;

import org.character.iras.Application;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Entity.User;
import org.character.iras.Exceptions.UserNotFoundException;

public class AuthenticationService {
    protected String username;
    protected String password;

    public AuthenticationService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * 验证账户密码是否匹配
     * @return 如果匹配，返回true，否则返回false
     * @throws UserNotFoundException 账户不存在
     */
    public boolean verify() throws UserNotFoundException {
        MySQLUserDataAccess mySQLUserDataAccess = Application.context.getBean(MySQLUserDataAccess.class);
        User user = mySQLUserDataAccess.getUserByUsername(this.username);
        if(user == null) throw new UserNotFoundException();
        return user.getPassword().equals(this.password);
    }
}

