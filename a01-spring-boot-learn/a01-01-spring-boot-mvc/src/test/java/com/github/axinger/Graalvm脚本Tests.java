package com.github.axinger;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Graalvm脚本Tests {

    @Test
    public void test() {
        try (Context context = Context.create()) {
            // 定义一个 JS 函数
            context.eval("js", "function greet(name) { return 'Hello, ' + name; }");

            // 调用函数并传参
            Value greetFunction = context.getBindings("js").getMember("greet");
            Value greeting = greetFunction.execute("World");
            System.out.println(greeting.asString()); // 输出: Hello, World
        }
    }


    @Test
    public void test2() {
        try (Context context = Context.create()) {
            // 读取 resources/script.js
            ClassPathResource resource = new ClassPathResource("test.js");
            Source source = Source.newBuilder("js", resource.getFile()).build();
            Value result = context.eval(source);
            System.out.println("读取js结果=" + result); // 输出脚本的执行结果
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test3() {
        int num1 = 5;
        int num2 = 3;

        try (Context context = Context.create()) {
            // 1. 从classpath加载JS文件
            ClassPathResource resource = new ClassPathResource("test.js");
            String jsCode = FileCopyUtils.copyToString(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            );

            // 2. 执行JS代码（定义函数）
            context.eval("js", jsCode);

            // 3. 调用函数
            Value sumFunction = context.getBindings("js").getMember("sum");
            Value result = sumFunction.execute(num1, num2);

            System.out.println("文件加载执行结果: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
