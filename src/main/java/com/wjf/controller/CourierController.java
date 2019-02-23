package com.wjf.controller;


import com.wjf.po.Courier;
import com.wjf.service.CourierService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/courier")
public class CourierController {

    CourierService as = new CourierService();

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Map register(@RequestParam(value="courierName",required=true) String courierName,
                     @RequestParam(value="courierCall",required=true) String courierCall,
                     @RequestParam(value="courierIdCard",required=true) String courierIdCard){//添加}
        Courier order = new Courier();
        order.setCourierName(courierName);
        order.setCourierCall(courierCall);
        order.setCourierIdCard(courierIdCard);
        return as.register(order);

    }


    @RequestMapping(value="/updateCourier",method = RequestMethod.POST)
    @ResponseBody
    public Map updateCourier(@RequestParam(value="courierId",required=true) int courierId,
                             @RequestParam(value="courierName",required=false) String courierName,
                             @RequestParam(value="courierCall",required=false) String courierCall,
                             @RequestParam(value="courierIdCard",required=false) String courierIdCard){//修改密码，用户名}
        Courier order = new Courier();
        order.setCourierId(courierId);
        order.setCourierName(courierName);
        order.setCourierCall(courierCall);
        order.setCourierIdCard(courierIdCard);
        return as.updateCourier(order);

    }
    @RequestMapping(value="/getCourier",method = RequestMethod.POST)
    @ResponseBody
    public Map getCourier(@RequestParam(value="pageSize",required=true) int pageSize,
                         @RequestParam(value="pageIndex",required=true) int pageIndex,
                          @RequestParam(value="courierName",required=false) String courierName){//修改密码，用户名}
        Courier order = new Courier();
        order.setPageSize(pageSize);
        order.setPageIndex(pageIndex);
        order.setCourierName(courierName);
        return as.getCourier(order);

    }
    @RequestMapping(value="/deleteCourier",method = RequestMethod.POST)
    @ResponseBody
    public Map getManage(@RequestParam(value="id",required=true) int id){//
        Courier order = new Courier();
        order.setCourierId(id);
        return as.deleteCourier(order);

    }
}