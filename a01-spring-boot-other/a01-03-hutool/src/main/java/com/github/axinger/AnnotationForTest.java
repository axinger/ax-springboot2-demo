package com.github.axinger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
// Target注解决定MyAnnotation注解可以加在哪些成分上，如加在类身上，或者属性身上，或者方法身上等成分
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AnnotationForTest {

    /**
     * 注解的默认属性值
     *
     * @return 属性值
     */
    String value();

    String age();
}
