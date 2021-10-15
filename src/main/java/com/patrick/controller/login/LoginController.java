package com.patrick.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.patrick.bean.TblUserRecord;
import com.patrick.returnJson.Permission;
import com.patrick.returnJson.Permissions;
import com.patrick.returnJson.ReturnObject;
import com.patrick.returnJson.UserInfo;
import com.patrick.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController //一定要用RestController不要用Controller
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {}, allowCredentials = "true")
public class LoginController {

    @Autowired  //必须要自动装载一下，否则会报空指针异常
    private LoginService loginService;

    @RequestMapping("/auth/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        TblUserRecord tblUserRecord = loginService.login(username, password);
        tblUserRecord.setToken(tblUserRecord.getUserName());
        //将用户数据写入到Session中
        session.setAttribute("userRecord", tblUserRecord);
        System.out.println(session.getId());
        ReturnObject returnObject = new ReturnObject(tblUserRecord);
        return JSONObject.toJSONString(returnObject);
    }

    @RequestMapping("/user/info")
    public String getInfo(HttpSession session){
        System.out.println(session.getId());
        TblUserRecord tblUserRecord = (TblUserRecord) session.getAttribute("userRecord");

        System.out.println("RolePrivileges()" + tblUserRecord.getTblRole().getRolePrivileges() + "--" + tblUserRecord);

        //获取模块信息
        String[] split = tblUserRecord.getTblRole().getRolePrivileges().split("-");
        //创建权限集合对象
        Permissions permissions = new Permissions();
        //向集合对象中添加具体的权限
        List<Permission> permissionList = new ArrayList<>();
        for(String s : split){
            permissionList.add(new Permission(s));
        }
        permissions.setPermissions(permissionList);
        //设置返回值的result
        UserInfo userInfo = new UserInfo(tblUserRecord.getUserName(), permissions);
        return JSONObject.toJSONString(new ReturnObject(userInfo));
    }

    @RequestMapping("/auth/logout")
    public String  logOut(HttpSession session){
        System.out.println("LogOut!");
        session.invalidate();
        return JSONObject.toJSONString(new ReturnObject(null));
    }

}
