package org.character.iras.DataAccess.Interfaces;

import jakarta.annotation.Nullable;
import org.character.iras.Entity.User;
import java.util.Date;
import java.util.List;

/**
 * 用户数据连接接口
 */
public interface UserDataAccess extends DataAccess{

    /**
     * 通过用户名寻找一个用户
     * @param username 用户名
     * @return 用户实体。如果找不到用户，则返回值为<code>null</code>
     * @apiNote 返回值可空：如果找不到这个用户名的用户，则返回<code>null</code>
     */
    @Nullable
    User getUserByUsername(String username);

    /**
     * 通过邮箱地址寻找一个用户
     * @param email 邮箱地址
     * @return 用户实体。如果找不到用户，则返回值为<code>null</code>
     * @apiNote 返回值可空：如果找不到这个邮箱地址的用户，则返回<code>null</code>
     */
    @Nullable
    User getUserByEmail(String email);

    /**
     * 通过简历ID寻找一个用户
     * @param resumeId 简历ID
     * @return 用户实体。如果找不到用户，则返回值为<code>null</code>
     * @apiNote 返回值可空：如果找不到这个简历ID的用户，则返回<code>null</code>
     */
    @Nullable
    User getUserByResumeId(int resumeId);

    /**
     * 通过条件寻找一个用户
     * @param condition 条件表达式
     * @return 用户实体。如果找不到用户，则返回值为<code>null</code>
     * @apiNote 返回值可空：如果找不到这个条件的用户，则返回<code>null</code>。此处条件表达式应该改是一个数据库语句。
     * @implNote 通过连接数据库通过一些手段回调数据库API实现。
     */
    @Nullable
    List<User> getUser(String condition) throws Exception;

    /**
     * 设置一个用户最后的登陆时间
     * @param username 用户名
     * @param date 登陆时间
     */
    void setUserLastLoginTime(String username, Date date);

    /**
     * 设置一个用户最后使用的令牌
     * @param username 用户名
     * @param token 令牌
     */
    void setUserLastToken(String username, String token);

    List<User> getUsers();
}
