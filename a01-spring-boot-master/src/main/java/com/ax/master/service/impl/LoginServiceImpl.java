package com.ax.master.service.impl;


import com.ax.master.entity.IpLog;
import com.ax.master.entity.Userinfo;
import com.ax.master.mapper.IpLogMapper;
import com.ax.master.mapper.UserinfoMapper;
import com.ax.master.service.ILoginService;
import com.ax.master.util.axUtil.AxConst;
import com.ax.master.util.axUtil.AxJwtUtil;
import com.ax.master.util.error.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author axing
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private IpLogMapper ipLogMapper;

    @Override
    public Userinfo getByUserName(String username) {

        Userinfo userinfo = this.userinfoMapper.getByUserName(username);
        return userinfo;
    }

    @Override
    public Userinfo getById(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public Userinfo login(String username, String password, HttpServletRequest request) throws TokenException {

        Userinfo userinfo = this.userinfoMapper.getByUserName(username.toLowerCase());

        System.out.println("查询用户信息 userinfo = " + userinfo);

        if (userinfo == null) {
            throw new TokenException("账号不存在");
        }


        /*记录登录成功或失败*/
        IpLog ipLog = new IpLog();
        ipLog.setIp(request.getRemoteAddr());
        ipLog.setLoginTime(new Date());
        ipLog.setUserName(username.toLowerCase());
        ipLog.setUserinfoId(userinfo.getId());

        /**equalsIgnoreCase 忽略大小写**/
        if (!userinfo.getPassword().equalsIgnoreCase(password)) {
            ipLog.setLoginState(0);
            int insert = ipLogMapper.insert(ipLog);
            System.out.println("插入登录ip = " + insert);

            throw new TokenException("账号或者密码错误");
        }


        userinfo.setToken(AxJwtUtil.createJWT(userinfo.getId().toString(), username, password, 0));
        /**登陆成功,保存当前登陆的userinfo Session 使用redis共享存储*/
//        UserinfoContext.setUserinfoSession(userinfo);
        ipLog.setLoginState(1);


        int insert = ipLogMapper.insert(ipLog);
        System.out.println("插入登录ip = " + insert);


        // 验证身份和登陆
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
//        // 设置记住我
////            token.setRememberMe(true);
//        //进行登录操作
//        subject.login(token);

        return userinfo;
    }

    @Override
    public boolean hasAdmin() {

        return this.userinfoMapper.getCountByUserName(AxConst.ADMIN_NAME) > 0;

    }

    @Override
    public void createAdmin() {

        if (!hasAdmin()) {

            Userinfo userinfo = new Userinfo();
            userinfo.setUserName(AxConst.ADMIN_NAME);
            userinfo.setPassword(AxConst.ADMIN_PASSWORD);
//            userinfo.setUserType(Userinfo.USERTYPE_SYSTEM);
            this.userinfoMapper.insert(userinfo);

        }

    }
}
