package com.wjf.controller;

import com.wjf.po.AdminInfo;
import com.wjf.po.Express;
import com.wjf.po.Gool;
import com.wjf.service.ExpressService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/express")
public class ExpressController {
    ExpressService es = new ExpressService();
    @RequestMapping(value="/getExpressByAdminId",method = RequestMethod.GET)
    @ResponseBody
    public Map getExpressByAdminId(@RequestParam(value="adminId",required=true) int adminId,
                                   @RequestParam(value="expressType",required=true) String expressType,
                                   @RequestParam(value="flag",required=true) int flag){//查快递，expressType（已发货，未发货，已签收，全部
                                                                                    //flag（0==全部，1=寄的，2=收的）
        Express express = new Express();
        express.setExpressFromId(adminId);
        express.setExpressToId(adminId);
        express.setExpressType(expressType);
        express.setFlag(flag);
        return es.getExpressByAdminId(express);

    }

    @RequestMapping(value="/insertExpress",method = RequestMethod.POST)
    @ResponseBody
    public Map insertExpress(@RequestParam(value="goolName",required=true) String goolNmae,
                        @RequestParam(value="expressFrom",required=true) String expressFrom,
                        @RequestParam(value="expressTo",required=true) String expressTo,
                        @RequestParam(value="expressTime",required=true) String expressTime,
                        @RequestParam(value="expressFromId",required=true) int expressFromId,
                        //@RequestParam(value="expressToId",required=true) String expressToId,
                        @RequestParam(value="adminName",required=true) String adminName,
                        @RequestParam(value="adminCell",required=true) String adminCell,
                        @RequestParam(value="expressPlace",required=true) String expressPlace){//申请寄快递，
        Express express = new Express();
        express.setExpressFrom(expressFrom);
        express.setExpressTo(expressTo);
        express.setExpressTime(expressTime);
        express.setExpressType("申请中");
        express.setExpressFromId(expressFromId);
        express.setExpressPlace(expressPlace);

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminName(adminName);
        adminInfo.setAdminCell(adminCell);
        adminInfo.setAdminPlace(expressPlace);
        adminInfo.setAdminRoot("0");

        Gool gool = new Gool();
        gool.setGoolName(goolNmae);
        return es.insertExpress(express,adminInfo,gool);

    }

    @RequestMapping(value="/getExpress",method = RequestMethod.GET)
    @ResponseBody
    public Map getExpress(@RequestParam(value="adminName",required=false) String adminName,
                          @RequestParam(value="expressCode",required=false) String expressCode,
                                   @RequestParam(value="expressType",required=true) String expressType,
                                   @RequestParam(value="pageSize",required=false) int pageSize,
                                   @RequestParam(value="pageIndex",required=false) int pageIndex,
                                   @RequestParam(value="flag",required=true) int flag,
                          @RequestParam(value="courierId",required=false) int courierId){//查快递，expressType（已发货，未发货，已签收，全部
        //flag（0==全部，1=寄的，2=收的）
        Express express = new Express();
        express.setExpressType(expressType);
        express.setExpressCode(expressCode);
        express.setFlag(flag);
        express.setPageSize(pageSize);
        express.setPageIndex(pageIndex);
        express.setCourierId(courierId);
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminName(adminName);
        return es.getExpress(express,adminInfo);

    }
    @RequestMapping(value="/getExpressByExpressCode",method = RequestMethod.GET)
    @ResponseBody
    public Map getExpressByExpressCode(@RequestParam(value="expressCode",required=true) String expressCode){//查快递，expressType（已发货，未发货，已签收，全部
        //flag（0==全部，1=寄的，2=收的）
        Express express = new Express();
        express.setExpressCode(expressCode);
        return es.getExpressByExpressCode(express);

    }
    @RequestMapping(value="/updateExpress",method = RequestMethod.POST)
    @ResponseBody
    public Map updateExpress(@RequestParam(value="expressId",required=true) int expressId,
                             @RequestParam(value="goolId",required=true) int goolId,
                             @RequestParam(value="goolName",required=false) String goolName,
                             @RequestParam(value="goolType",required=false) String goolType,
                             @RequestParam(value="goolWeight",required=false) String goolWeight,
                             @RequestParam(value="expressFrom",required=false) String expressFrom,
                             @RequestParam(value="expressTo",required=false) String expressTo,
                             @RequestParam(value="expressTime",required=false) String expressTime,
                             @RequestParam(value="expressType",required=false) String expressType,
                             //@RequestParam(value="expressToId",required=true) String expressToId,
//                             @RequestParam(value="adminName",required=true) String adminName,
                             @RequestParam(value="expressPlace",required=false) String expressPlace){//查快递，expressType（已发货，未发货，已签收，全部
        //flag（0==全部，1=寄的，2=收的）
        Express express = new Express();
        express.setExpressId(expressId);
        express.setExpressFrom(expressFrom);
        express.setExpressTo(expressTo);
        express.setExpressTime(expressTime);
        express.setExpressType(expressType);
        express.setExpressPlace(expressPlace);
        Gool gool = new Gool();
        gool.setGoolId(goolId);
        gool.setGoolName(goolName);
        gool.setGoolType(goolType);
        gool.setGoolWeight(goolWeight);
        System.out.println(expressType);
        return es.updateExpress(express,gool);

    }
}
