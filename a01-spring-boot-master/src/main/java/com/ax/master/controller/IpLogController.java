package com.ax.master.controller;

import com.ax.master.entity.IpLog;
import com.ax.master.po.UpdateListObject;
import com.ax.master.service.IIpLogService;
import com.axing.common.response.result.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author axing
 */
@RestController
@Tag(name = "IpLogController", description = "查看登录ip")
public class IpLogController {

    @Autowired
    IIpLogService ipLogService;

    /**
     * PageInfo 含有页面信息
     */
    @RequestMapping(value = "/ipLogPageInfo.do")
    public Object ipLogPageInfo(@RequestParam(value = "pageNum") int pageNum,
                                @RequestParam(value = "pageSize") int pageSize) {
        return ipLogService.findByPageInfo(pageNum, pageSize);

    }

    /**
     * ipLogPage 只有数据
     */
    @RequestMapping(value = "/ipLogPage.do")
    public Object ipLogPage(@RequestParam(value = "pageNum") int pageNum,
                            @RequestParam(value = "pageSize") int pageSize) {

        return ipLogService.findByPage(pageNum, pageSize);

    }

    /**
     * ipLogPage 只有数据
     */
    @RequestMapping(value = "/ipLogAll.do")
    public Object ipLogAll() {
        return ipLogService.findAll();
    }

    @RequestMapping(value = "/getIpLog.do")
    public Object getByKey(Long id) {
        System.out.println("id = " + id);
        IpLog ipLog = ipLogService.getByKey(id);
        System.out.println("ipLog = " + ipLog);
        return ipLog;
//        return ipLogService.getByKeyResultEntity(id);
    }

    @RequestMapping(value = "/updateIplog.do")
    public int updateByEntity(IpLog ipLog) {

        return ipLogService.updateByEntity(ipLog);
    }

    @RequestMapping(value = "/updateList.do")
    public Object updateByList(@RequestBody(required = false) List<IpLog> list) {
        System.out.println("list = " + list);
        return ipLogService.updateByListWhen(list);
    }

    @RequestMapping(value = "/updateList2.do")
    public Object updateByList2(@RequestBody(required = false) UpdateListObject object) {

        System.out.println("name = " + object);

        return Result.ok();
    }

    @RequestMapping(value = "/list.do")
    public void listParam(@RequestBody List<String> list, String name) {

        System.out.println("list = " + list);
        System.out.println("name = " + name);
    }


    @RequestMapping(value = "/ip.do")
    public Object ip(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        Map map = new HashMap();
        map.put("ip", ip);
        map.put("localAddr", request.getLocalAddr());
        map.put("remoteAddr", request.getRemoteAddr());
        map.put("contextPath", request.getContextPath());
        map.put("serverPort", request.getServerPort());
        return map;
    }


}

