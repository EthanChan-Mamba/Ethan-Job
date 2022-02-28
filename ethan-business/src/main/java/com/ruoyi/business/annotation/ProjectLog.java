package com.ruoyi.business.annotation;

import java.lang.annotation.*;

/**
 * @author chen
 * @version 1.0.0
 * @ClassName ProjectLog.java
 * @Description 记录项目动态日志
 * @createTime 2022-02-28 09:31
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProjectLog {
    // 日志描述
    String description() default "";

    // 操作类型
    int tableType() default 0;
}
