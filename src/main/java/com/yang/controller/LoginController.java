package com.yang.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.yomahub.tlog.core.annotation.TLogAspect;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 登录相关控制器
 * @Author: Guo.Yang
 * @Date: 2022/11/25/16:57
 */
@RequestMapping("/user")
@RestController
public class LoginController {

    @RequestMapping("/doLogin")
    @TLogAspect(str = "XYZ")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 返回token
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/getTokenInfo")
    public SaResult getTokenInfo(String username, String password){
        if("gy".equals(username) && "123".equals(password)){
            StpUtil.login(10001);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("获取token失败");
    }
}
