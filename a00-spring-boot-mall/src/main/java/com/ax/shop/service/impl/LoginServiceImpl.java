package com.ax.shop.service.impl;


import com.ax.shop.context.UserinfoContext;
import com.ax.shop.entity.IpLog;
import com.ax.shop.entity.Userinfo;
import com.ax.shop.mapper.IpLogMapper;
import com.ax.shop.mapper.UserinfoMapper;
import com.ax.shop.service.ILoginService;
import com.ax.shop.util.axUtil.AxConst;
import com.ax.shop.util.axUtil.AxJwtUtil;
import com.ax.shop.util.axUtil.AxResultEntity;
import com.ax.shop.util.axUtil.AxResultStateEnum;
import com.ax.shop.util.error.TokenException;
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

        Userinfo userinfo = this.userinfoMapper.getByusername(username);
        return userinfo;
    }

    @Override
    public Userinfo getById(Long id) {
        return userinfoMapper.selectByPrimaryKey(id);
    }


    @Override
    public Userinfo login(String username, String password, HttpServletRequest request) throws TokenException {

        Userinfo userinfo = this.userinfoMapper.getByusername(username.toLowerCase());

        System.out.println("查询用户信息 userinfo = " + userinfo);

        AxResultEntity<Userinfo> responseEntity = new AxResultEntity();

        if (userinfo == null) {
            responseEntity.setStateEnum(AxResultStateEnum.FAILURE);
            responseEntity.setMsg("账号不存在");
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
            responseEntity.setStateEnum(AxResultStateEnum.INVALID);
            responseEntity.setMsg("账号或者密码错误");
            int insert = ipLogMapper.insert(ipLog);
            System.out.println("插入登录ip = " + insert);

            throw new TokenException("账号或者密码错误");
        }


        userinfo.setToken(AxJwtUtil.createJWT(userinfo.getId().toString(), username, password, 0));
        /**登陆成功,保存当前登陆的userinfo*/
        UserinfoContext.putUserinfo(userinfo);
        ipLog.setLoginState(1);
        responseEntity.setStateEnum(AxResultStateEnum.SUCCESS);
        responseEntity.setBody(userinfo);

        int insert = ipLogMapper.insert(ipLog);
        System.out.println("插入登录ip = " + insert);


        //验证身份和登陆
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

        return this.userinfoMapper.getCountByusername(AxConst.ADMIN_NAME) > 0;

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
