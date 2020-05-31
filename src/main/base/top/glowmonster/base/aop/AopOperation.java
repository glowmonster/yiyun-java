package top.glowmonster.base.aop;

import top.glowmonster.base.em.OperationModuleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 标记注解用于方法
@Target(ElementType.METHOD)
// 可以反射获得注解参数
@Retention(RetentionPolicy.RUNTIME)
public @interface AopOperation {
    // 注解描述
    String desc() default "";
    String type();
    OperationModuleEnum module();
}
