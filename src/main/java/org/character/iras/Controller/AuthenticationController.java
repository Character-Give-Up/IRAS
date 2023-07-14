package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.character.iras.DataAccess.Interfaces.TokenDataAccess;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLTokenDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Entity.Token;
import org.character.iras.Entity.User;
import org.character.iras.Exceptions.UserNotFoundException;
import org.character.iras.Service.AuthenticationService;
import org.character.iras.Utils.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * 用户账户验证控制器
 */
@RestController
public class AuthenticationController {
    /**
     * 验证服务实例
     */
    private AuthenticationService authenticationService;
    /**
     * 随机令牌生成器实例
     */
    private TokenGenerator tokenGenerator;
    /**
     * 令牌数据连接层实例
     */
    private TokenDataAccess tokenDataAccess;
    /**
     * 用户数据连接层实例
     */
    private UserDataAccess userDataAccess;

    /**
     * 自动注入用户账户验证服务实例
     * @param service 用户账户验证服务实例
     */
    @Autowired
    public void setAuthenticationService(AuthenticationService service){
        this.authenticationService = service;
    }

    /**
     * 自动注入随机令牌生成器实例
     * @param generator 随机令牌生成器实例
     */
    @Autowired
    public void setTokenGenerator(TokenGenerator generator){
        this.tokenGenerator = generator;
    }

    /**
     * 自动注入令牌数据连接层实例
     * @param access 令牌数据连接层实例
     */
    @Autowired
    public void setTokenDataAccess(MySQLTokenDataAccess access){
        this.tokenDataAccess = access;
    }

    /**
     * 自动注入用户数据连接层实例
     * @param access 用户数据连接层实例
     */
    @Autowired
    public void setUserDataAccess(MySQLUserDataAccess access){
        this.userDataAccess = access;
    }


    /**
     * 处理登录请求
     * @param info 请求体
     * @return 登陆结果
     */
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject info,
                            HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger("登录请求处理器");
        String username = info.getString("username");
        String password = info.getString("password");
        authenticationService.setUsername(username);
        authenticationService.setPassword(password);
        // 准备返回结果，初始化JSON对象
        JSONObject result = new JSONObject();
        // 准备验证结果
        boolean auth;
        // 尝试验证，因为authenticationService.verify()在账户不存在的情况下，会抛出UserNotFoundException异常
        try {
            // 尝试验证并赋值
            auth = authenticationService.verify();
        }catch (UserNotFoundException e) {
            // 找不到用户，则返回-1，并告知前端用户不存在
            result.put("code", -1);
            result.put("message", "用户不存在");
            logger.info("核验用户" + username + "不存在");
            // 并直接返回结果，此时函数将不会继续执行
            return result;
        }

        if(auth){
            // 如果验证成功，则返回1，并返回令牌值
            logger.info("核验用户" + username + "成功！");
            result.put("code", 1);
            this.tokenGenerator.setDigit(64);
            String token = this.tokenGenerator.generate();
            result.put("token", token);
            LoginUser(response, username, token);
        }else {
            // 如果验证失败，则返回0，并告知前端账号或密码错误
            logger.info("核验用户" + username + "失败！");
            result.put("code", 0);
            result.put("message", "账号或密码错误");
        }

        //最终返回结果
        return result;

    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody JSONObject info,
                               HttpServletResponse response){
        JSONObject result = new JSONObject();
        if(login(info, response).getInteger("code") == -1){
            String username = info.getString("username");
            String password = info.getString("password");
            String email = info.getString("email");
            userDataAccess.addUser(username, password, email);
            result.put("code", 1);
            result.put("message", "注册成功");
            String token = tokenGenerator.generate();
            LoginUser(response, username, token);
        }else{
            result.put("code", -1);
            result.put("message", "用户" + info.getString("username") + "已存在");
        }
        return result;
    }

    /**
     * 让一个用户登录
     * @param response 响应体
     * @param username 用户名
     * @param token 登陆令牌
     */
    private void LoginUser(HttpServletResponse response, String username, String token) {
        tokenDataAccess.addToken(token);
        userDataAccess.setUserLastLoginTime(username, Calendar.getInstance().getTime());
        userDataAccess.setUserLastToken(username, token);
        Cookie cookie = new Cookie("token", token);
        Cookie cookie1 = new Cookie("username", username);
        response.addCookie(cookie);
        response.addCookie(cookie1);
    }

    @GetMapping("/isPrivileged")
    public JSONObject isPrivileged(HttpServletRequest request){
        JSONObject res = new JSONObject();
        Cookie cookie = getCookie(request.getCookies(), "token");
        if(cookie == null){
            res.put("code", -1);
            res.put("message", "没有令牌");
        }else {
            String token = cookie.getValue();
            User user = userDataAccess.findUserByLastLoginToken(token);
            if(user == null){
                res.put("code", 0);
                res.put("message", "令牌无效");
            }else {
                res.put("code", 1);
                res.put("privileged", user.isPrivileged());
            }
        }
        return res;
    }


    private Cookie getCookie(Cookie[] cookies, String name){
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)) return cookie;
        }
        return null;
    }


}
