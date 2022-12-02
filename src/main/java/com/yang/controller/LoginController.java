package com.yang.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson.JSONObject;
import com.yang.config.SaTokenConfigure;
import com.yomahub.tlog.core.annotation.TLogAspect;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public SaResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001, SaLoginConfig.setExtra("name","zhang"));
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.error();
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @RequestMapping("/getTokenExtra")
    public String getTokenExtra(HttpServletRequest request){
        String accessToken = request.getHeader("accessToken");
        Object name = StpUtil.getExtra(accessToken, "name");
        Object loginId = StpUtil.getLoginIdByToken(accessToken);
        return "当前登录的姓名为====> " + name + " " + JSONObject.toJSONString(loginId);
    }
}
