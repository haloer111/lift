package com.gexiao.lift.controller;

import com.alibaba.fastjson.JSON;
import com.gexiao.lift.common.Result;
import com.gexiao.lift.entity.User;
import com.gexiao.lift.service.IUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.PrettyFormat;
import static com.alibaba.fastjson.serializer.SerializerFeature.WriteDateUseDateFormat;

/**
 * @Auther: gexiao
 * @Date: 2019/4/9 15:23
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserControllerUserAuth extends UserAuthBaseController<IUserService, User> {

    /**
     * 登录
     * @param body { loginName -> 用户名, loginPass -> 密码 }
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Map<String, String> body) {
        return Result.ok(service.login(body.get("loginName"), body.get("loginPass")));
    }

    /**
     * 修改密码
     * @param body { old -> 旧密码 , new -> 新密码}
     * @return
     */
    @PostMapping("update/password")
    public Result updatePassword(@RequestBody Map<String, String> body) {
        return Result.ok(service.updatePassword(body.get("old"), body.get("new")));
    }

    @GetMapping("logout")
    public Result logout(@RequestParam String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(Base64.getEncoder().encodeToString("gexiao".getBytes())).parseClaimsJws(token);
        claims.getBody().setId("x");
        return null;
    }


    public static void main(String[] args) {
        User e = new User();
        e.setName("");
        e.setAlias("");
        e.setTel("");
        e.setLoginName("");
        e.setLoginPassword("");
        e.setCreateAndUpdateTime();
        e.setId("");
        e.setCreateTime(new Date());
        e.setUpdateTime(new Date());

        System.out.println(JSON.toJSONString(e, PrettyFormat, WriteDateUseDateFormat));
    }
}
