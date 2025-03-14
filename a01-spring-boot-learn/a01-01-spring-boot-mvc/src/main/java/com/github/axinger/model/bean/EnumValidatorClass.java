//package com.github.axinger.bean;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
/// **
// * 枚举值校验注解实现
// *
// * @author: zetting
// * @date:2018/12/18
// */
//public class EnumValidatorClass implements ConstraintValidator<EnumValidator, Object>, Annotation {
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//    private List<Object> values = new ArrayList<>();
//
//    @Override
//    public void initialize(EnumValidator enumValidator) {
//        Class<?> clz = enumValidator.value();
//        Object[] ojects = clz.getEnumConstants();
//        try {
//            Method method = clz.getMethod("getId");
//            if (Objects.isNull(method)) {
//
//                throw new Exception(String.format("枚举对象{}缺少字段名为value的字段",
//                        clz.getName()));
//            }
//            Object value = null;
//            for (Object obj : ojects) {
//                value = method.invoke(obj);
//                values.add(value);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("[处理枚举校验异常]", e);
//        }
//    }
//
//
//    @Override
//    public Class<? extends Annotation> annotationType() {
//        return null;
//    }
//
//    @Override
//    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
//        return Objects.isNull(value) || values.contains(value) ? true : false;
//    }
//}
//
