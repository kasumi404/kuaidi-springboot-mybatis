package com.wjf.controller;

import com.wjf.po.Express;
import com.wjf.po.Complaint;
import com.wjf.service.ComplaintService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    ComplaintService gs = new ComplaintService();
    @RequestMapping(value="/getComplaint",method = RequestMethod.POST)
    @ResponseBody
    public Map getComplaint(@RequestParam(value="pageSize",required=true) int pageSize,
                     @RequestParam(value="pageIndex",required=true) int pageIndex){//获取Complaint信息}
        Complaint order = new Complaint();
        order.setPageIndex(pageIndex);
        order.setPageSize(pageSize);
        return gs.getComplaint(order);

    }
    @RequestMapping(value="/updateComplaint",method = RequestMethod.POST)
    @ResponseBody
    public Map updateComplaint(@RequestParam(value="complaintId",required=true) int complaintId,
                       @RequestParam(value="adminId",required=true) int adminId,
                       @RequestParam(value="context",required=true) String context){//修改Complaint信息}
        Complaint order = new Complaint();
        order.setComplaintId(complaintId);
        order.setAdminId(adminId);
        order.setContext(context);
        return gs.updateComplaint(order);

    }


    @RequestMapping(value="/insertComplaint",method = RequestMethod.POST)
    @ResponseBody
    public Map insertComplaint(@RequestParam(value="adminId",required=true) int adminId,
                               @RequestParam(value="context",required=true) String context){//修改Complaint信息}
        Complaint order = new Complaint();
        order.setAdminId(adminId);
        order.setContext(context);
        return gs.insertComplaint(order);

    }
    @RequestMapping(value="/deleteComplaint",method = RequestMethod.POST)
    @ResponseBody
    public Map getManage(@RequestParam(value="id",required=true) int id){//
        Complaint order = new Complaint();
        order.setComplaintId(id);
        return gs.deleteComplaint(order);

    }
}
