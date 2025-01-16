package com.github.axinger;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    @SneakyThrows
    public static  void writeError(HttpServletResponse response,String msg){
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map=new HashMap<>(2);
        map.put("code", "201");
        map.put("msg", msg);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
