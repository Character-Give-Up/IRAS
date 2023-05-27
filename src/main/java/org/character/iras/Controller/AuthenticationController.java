package org.character.iras.Controller;

import com.alibaba.fastjson.JSONObject;
import org.character.iras.Exceptions.UserNotFoundException;
import org.character.iras.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;


    @Autowired
    public void setAuthenticationService(AuthenticationService service){
        this.authenticationService = service;
    }

    /**
     * 处理登录请求
     * @param username 用户名
     * @param password 密码
     * @return 登陆结果
     */
    @PostMapping("/login")
    public JSONObject login(@RequestParam("username") String username,
                            @RequestParam("password") String password){
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
            // 并直接返回结果，此时函数将不会继续执行
            return result;
        }

        if(auth){
            // 如果验证成功，则返回1，并返回令牌值
            result.put("code", 1);
            // TODO: 2023/5/1 增加令牌生成器
            // new TokenGenerator(int digit);
        }else {
            // 如果验证失败，则返回0，并告知前端账号或密码错误
            result.put("code", 0);
            result.put("messgae", "账号或密码错误");
        }

        //最终返回结果
        return result;

    }
}
