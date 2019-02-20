package com.wjf.controller;


import com.wjf.po.Admin;
import com.wjf.po.AdminInfo;
import com.wjf.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AdminService as = new AdminService();
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestParam(value="username",required=true) String username,
                     @RequestParam(value="password",required=true) String password){//登陆验证}
        Admin order = new Admin();
        order.setUsername(username);
        order.setPassword(password);
        System.out.println("获得"+username+password);
        return as.login(order);

    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestParam(value="username",required=true) String username,
                     @RequestParam(value="password",required=true) String password,
                     @RequestParam(value="adminName",required=true) String adminName,
                        @RequestParam(value="adminCell",required=true) String adminCell,
                        @RequestParam(value="adminIDCard",required=true) String adminIDCard,
                        @RequestParam(value="adminPlace",required=true) String adminPlace){//注册}
        Admin order = new Admin();
        order.setUsername(username);
        order.setPassword(password);
        AdminInfo order2 = new AdminInfo();
        order2.setAdminName(adminName);
        order2.setAdminCell(adminCell);
        order2.setAdminIDCard(adminIDCard);
        order2.setAdminPlace(adminPlace);
        return as.register(order,order2);

    }

    @RequestMapping(value="/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map edit(@RequestParam(value="adminId",required=true) int id,
                        @RequestParam(value="adminName",required=false) String adminName,
                        @RequestParam(value="adminCell",required=false) String adminCell,
                        @RequestParam(value="adminIDCard",required=false) String adminIDCard,
                        @RequestParam(value="adminPlace",required=false) String adminPlace){//编辑}
        Admin order = new Admin();
        order.setId(id);
        AdminInfo order2 = new AdminInfo();
        order2.setAdminId(id);
        order2.setAdminName(adminName);
        order2.setAdminCell(adminCell);
        order2.setAdminIDCard(adminIDCard);
        order2.setAdminPlace(adminPlace);
        return as.edit(order,order2);

    }

    @RequestMapping(value="/updateAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAdmin(@RequestParam(value="id",required=true) int id,
                    @RequestParam(value="username",required=false) String username,
                    @RequestParam(value="password",required=false) String password){//修改密码，用户名}
        Admin order = new Admin();
        order.setId(id);
        order.setUsername(username);
        order.setPassword(password);
        return as.updateAdmin(order);

    }

    @RequestMapping(value="/getAdmin",method = RequestMethod.POST)
    @ResponseBody
    public Map getAdmin(@RequestParam(value="adminName",required=false) String adminName,
                        @RequestParam(value="pageSize",required=true) int pageSize,
                        @RequestParam(value="pageIndex",required=true) int pageIndex){//获得用户信息}
        AdminInfo order = new AdminInfo();
        order.setAdminName(adminName);
        order.setPageIndex(pageIndex);
        order.setPageSize(pageSize);
        return as.getAdminInfo(order);

    }
}