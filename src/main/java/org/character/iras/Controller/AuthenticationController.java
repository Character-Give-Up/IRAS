package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.character.iras.DataAccess.Interfaces.TokenDataAccess;
import org.character.iras.DataAccess.Interfaces.UserDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLTokenDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Exceptions.UserNotFoundException;
import org.character.iras.Service.AuthenticationService;
import org.character.iras.Utils.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private TokenGenerator tokenGenerator;
    private TokenDataAccess tokenDataAccess;
    private UserDataAccess userDataAccess;


    @Autowired
    public void setAuthenticationService(AuthenticationService service){
        this.authenticationService = service;
    }

    @Autowired
    public void setTokenGenerator(TokenGenerator generator){
        this.tokenGenerator = generator;
    }

    @Autowired
    public void setTokenDataAccess(MySQLTokenDataAccess access){
        this.tokenDataAccess = access;
    }

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
            tokenDataAccess.addToken(token);
            userDataAccess.setUserLastLoginTime(username, Calendar.getInstance().getTime());
            userDataAccess.setUserLastToken(username, token);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        }else {
            // 如果验证失败，则返回0，并告知前端账号或密码错误
            logger.info("核验用户" + username + "失败！");
            result.put("code", 0);
            result.put("message", "账号或密码错误");
        }

        //最终返回结果
        return result;

    }
}
