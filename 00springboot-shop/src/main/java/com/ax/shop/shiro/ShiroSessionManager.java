// +----------------------------------------------------------------------
// | JavaWeb_Layui混编版框架 [ JavaWeb ]
// +----------------------------------------------------------------------
// | 版权所有 2021 上海JavaWeb研发中心
// +----------------------------------------------------------------------
// | 官方网站: http://www.javaweb.vip/
// +----------------------------------------------------------------------
// | 作者: 鲲鹏 <javaweb520@gmail.com>
// +----------------------------------------------------------------------
// | 免责声明:
// | 本软件框架禁止任何单位和个人用于任何违法、侵害他人合法利益等恶意的行为，禁止用于任何违
// | 反我国法律法规的一切平台研发，任何单位和个人使用本软件框架用于产品研发而产生的任何意外
// |  、疏忽、合约毁坏、诽谤、版权或知识产权侵犯及其造成的损失 (包括但不限于直接、间接、附带
// | 或衍生的损失等)，本团队不承担任何法律责任。本软件框架已申请版权保护，任何组织、单位和个
// | 人不得有任何侵犯我们的版权的行为(包括但不限于分享、转售、恶意传播，开源等等)，否则产生
// | 的一切后果和损失由侵权者全部承担，本软件框架只能用于公司和个人内部的法律所允许的合法合
// | 规的软件产品研发，详细声明内容请阅读《框架免责声明》附件；
// +----------------------------------------------------------------------

package com.ax.shop.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义获取Token
 */
public class ShiroSessionManager extends DefaultWebSessionManager {

    //定义常量
    private static final String AUTHORIZATION = "token";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    //重写构造器
    public ShiroSessionManager() {
        super();
        this.setDeleteInvalidSessions(true);
    }

    /**
     * 重写方法实现从请求头获取Token便于接口统一
     * 每次请求进来,Shiro会去从请求头找Authorization这个key对应的Value(Token)
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中存在token 则从请求头中获取token
        if (!StringUtils.isEmpty(token)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return token;
        } else {
            // 这里禁用掉Cookie获取方式
            // 按默认规则从Cookie取Token
            // return super.getSessionId(request, response);
            return null;
        }
//        //将ServletRequest转换成HttpServletRequest
//        String sessionId = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
//        if (sessionId != null) {
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "cookie");
////
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
////            //automatically mark it valid here.  If it is invalid, the
////            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
////            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
//
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
//                    ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
//
//            //automatically mark it valid here.  If it is invalid, the
//            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
//            return sessionId;
//        } else {
//            return super.getSessionId(request, response);
//        }
    }
}
