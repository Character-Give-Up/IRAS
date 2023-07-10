package org.character.iras.Entity;

import jakarta.annotation.Nullable;

import java.util.Date;

/**
 * 用户实体类
 */
public class User {
    private String username;
    private String password;
    @Nullable
    private String email;
    @Nullable
    private Date lastLogin;
    @Nullable
    private String lastToken;
    @Nullable
    private Resume resume;
    private int resumeId = -1;
    private boolean privileged;

    /**
     * 创建用户实体
     * @param username 用户名
     * @param password 密码
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username,
                String password,
                @Nullable String email,
                @Nullable Date lastLogin,
                @Nullable String lastToken) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLogin = lastLogin;
        this.lastToken = lastToken;
    }

    public User(String username,
                String password,
                @Nullable String email,
                @Nullable Date lastLogin,
                @Nullable String lastToken,
                int resumeId,
                boolean privileged) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLogin = lastLogin;
        this.lastToken = lastToken;
        this.resumeId = resumeId;
        this.privileged = privileged;
    }

    /**
     * 取回用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 取回用户密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(@Nullable Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Nullable
    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(@Nullable String lastToken) {
        this.lastToken = lastToken;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastLogin=" + lastLogin +
                ", lastToken='" + lastToken + '\'' +
                ", resumeId=" + resumeId +
                '}';
    }

    public boolean isPrivileged() {
        return privileged;
    }
}
