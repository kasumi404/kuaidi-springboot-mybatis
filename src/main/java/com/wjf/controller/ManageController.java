package com.wjf.controller;


import com.wjf.po.Manage;
import com.wjf.service.ManageService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/manage")
public class ManageController {

    ManageService as = new ManageService();
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam(value="username",required=true) String username,
                     @RequestParam(value="password",required=true) String password){//登陆验证}
        Manage order = new Manage();
        order.setUsername(username);
        order.setPassword(password);
        System.out.println("获得"+username+password);
        return as.login(order);

    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestParam(value="username",required=true) String username,
                     @RequestParam(value="password",required=true) String password,
                     @RequestParam(value="root",required=true) String root){//注册}
        Manage order = new Manage();
        order.setUsername(username);
        order.setPassword(password);
        order.setRoot(root);
        return as.register(order);

    }


    @RequestMapping(value="/updateManage",method = RequestMethod.POST)
    @ResponseBody
    public Map updateManage(@RequestParam(value="id",required=true) int id,
                    @RequestParam(value="username",required=false) String username,
                    @RequestParam(value="password",required=false) String password,
                    @RequestParam(value="root",required=false) String root){//修改密码，用户名}
        Manage order = new Manage();
        order.setId(id);
        order.setUsername(username);
        order.setPassword(password);
        order.setRoot(root);
        return as.updateManage(order);

    }
    @RequestMapping(value="/getManage",method = RequestMethod.POST)
    @ResponseBody
    public Map getManage(@RequestParam(value="pageSize",required=true) int pageSize,
                         @RequestParam(value="pageIndex",required=true) int pageIndex,
                            @RequestParam(value="username",required=false) String username,
                            @RequestParam(value="root",required=false) String root){//修改密码，用户名}
        Manage order = new Manage();
        System.out.println(pageSize+".."+pageIndex+".."+username+".."+root+".."+root+"..");
        order.setPageSize(pageSize);
        order.setPageIndex(pageIndex);
        order.setUsername(username);
        order.setRoot(root);
        return as.getManage(order);

    }
}