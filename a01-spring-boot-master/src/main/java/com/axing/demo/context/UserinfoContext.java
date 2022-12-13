package com.axing.demo.context;

import com.axing.demo.entity.Userinfo;
import com.axing.demo.util.axUtil.AxConst;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author axing
 */
public class UserinfoContext {

    private static HttpServletRequest getRequest() {

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static void setUserinfoSession(Userinfo userinfo) {
        getRequest().getSession().setAttribute(AxConst.USERINFO_IN_SESSION, userinfo);
    }

    public static Userinfo getCurrent() {
        return (Userinfo) getRequest().getSession().getAttribute(AxConst.USERINFO_IN_SESSION);

    }


}
