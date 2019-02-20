package com.wjf.controller;

import com.wjf.po.Express;
import com.wjf.po.Gool;
import com.wjf.service.GoolService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/gool")
public class GoolController {

    GoolService gs = new GoolService();
    @RequestMapping(value="/getGool",method = RequestMethod.POST)
    @ResponseBody
    public Map getGool(@RequestParam(value="pageSize",required=true) int pageSize,
                     @RequestParam(value="pageIndex",required=true) int pageIndex){//获取Gool信息}
        Gool order = new Gool();
        order.setPageIndex(pageIndex);
        order.setPageSize(pageSize);
        return gs.getGool(order);

    }
    @RequestMapping(value="/updateGool",method = RequestMethod.POST)
    @ResponseBody
    public Map updateGool(@RequestParam(value="goolId",required=true) int goolId,
                       @RequestParam(value="goolName",required=true) String goolName,
                       @RequestParam(value="goolType",required=true) String goolType,
                       @RequestParam(value="goolWeight",required=true) String goolWeight){//修改Gool信息}
        Gool order = new Gool();
        order.setGoolId(goolId);
        order.setGoolName(goolName);
        order.setGoolType(goolType);
        order.setGoolWeight(goolWeight);
        return gs.updateGool(order);

    }
    @RequestMapping(value="/updateGoolByexpressCode",method = RequestMethod.POST)
    @ResponseBody
    public Map updateGoolByexpressCode(@RequestParam(value="expressCode",required=true) String expressCode,
                          @RequestParam(value="goolType",required=true) String goolType,
                          @RequestParam(value="goolWeight",required=true) String goolWeight){//快递到快递站，添加Gool信息}
        Gool order = new Gool();
        order.setGoolType(goolType);
        order.setGoolWeight(goolWeight);
        Express express = new Express();
        express.setExpressCode(expressCode);
        System.out.println("获得"+expressCode);
        return gs.updateGoolByexpressCode(order,express);

    }
}
