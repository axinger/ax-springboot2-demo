//package com.github.axinger;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.SneakyThrows;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//public class ResponseUtil {
//
//    @SneakyThrows
//    public static void writeError(HttpServletResponse response, String msg) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, String> map = new HashMap<>(2);
//        map.put("code", "201");
//        map.put("msg", msg);
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.write(objectMapper.writeValueAsString(map));
////        getWriter() 返回一个 PrintWriter 对象，它用于写入字符（文本）数据。当你需要发送文本内容（如HTML、JSON、XML等）到客户端时，通常使用这个方法。
////        getOutputStream() 返回一个 ServletOutputStream 对象，它用于写入字节数据。当你需要发送非文本内容（如图片、文件下载等二进制数据）到客户端时，应该使用这个方法。
//        out.flush();
//        out.close();
//    }
//}
