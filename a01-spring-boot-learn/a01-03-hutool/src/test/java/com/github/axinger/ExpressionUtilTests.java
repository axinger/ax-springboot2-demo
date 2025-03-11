package com.github.axinger;

import cn.hutool.core.lang.Dict;
import cn.hutool.extra.expression.ExpressionUtil;
import org.junit.jupiter.api.Test;

public class ExpressionUtilTests {
    @Test
    void test() {
        final Dict dict = Dict.create()
                .set("a", 100.3)
                .set("b", 45)
                .set("c", -199.100);

// -143.8
        final Object eval = ExpressionUtil.eval("a-(b-c)", dict);

        System.out.println("eval = " + eval);
    }


//    @Test
//    void test2(){
//        ExpressionEngine engine = new JexlEngine();
//
//        final Dict dict = Dict.create()
//                .set("a", 100.3)
//                .set("b", 45)
//                .set("c", -199.100);
//
//// -143.8
//        final Object eval = engine.eval("a-(b-c)", dict);
//
//    }
}
