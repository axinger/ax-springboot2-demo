package axing.cloud.gateway.handler;

import axing.cloud.gateway.bean.FetchGatewayProperties;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class FetchGatewayInterceptor implements HandlerInterceptor {

    private FetchGatewayProperties properties;

    public FetchGatewayInterceptor(FetchGatewayProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!properties.getMustFetchGateway()) {
            return true;
        }
        String token = request.getHeader(properties.getFetchGatewayKey());
        String gatewayToken = properties.getFetchGatewayValue();
        if (ObjectUtil.equals(gatewayToken, token)) {
            return true;
        } else {
            throw new RuntimeException("请通过网关访问资源");
        }
    }

}

