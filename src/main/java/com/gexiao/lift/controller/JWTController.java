package com.gexiao.lift.controller;

import com.gexiao.lift.common.Result;
import com.gexiao.lift.util.JWTUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: gexiao
 * @Date: 2019/4/12 09:38
 * @Description:
 */
@RestController
@RequestMapping("jwt")
public class JWTController {


    /**
     * 刷新重置用户token
     *
     * @param request
     * @return
     */
    @GetMapping("/refresh")
    public Result refreshToken(HttpServletRequest request) {
        JWTUtil.UserInfo user = JWTUtil.getUserByRequest(request);
        if (user == null) {
            return Result.fail("用户token异常");
        }
        return Result.ok(JWTUtil.generateToken(user));
    }


    /**
     * 校验用户token
     *
     * @param request
     * @return
     */
    @GetMapping("/verify")
    public Result verifyToken(HttpServletRequest request) {
        JWTUtil.UserInfo user = JWTUtil.getUserByRequest(request);
        if (user == null)
            return Result.fail();
        return Result.ok();
    }
}
