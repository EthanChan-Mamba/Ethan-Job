package com.ruoyi.business.annotation;

import com.ruoyi.business.enums.BusinessTypeWithName;
import com.ruoyi.common.enums.BusinessType;

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

    /** 日志动态类型
     * @return*/
    BusinessTypeWithName logType() default BusinessTypeWithName.OTHER;
    //
    // /** 动态内容 */
    // String projectNewsContent() default "";
    //
    // /** 项目id */
    // Integer projectId;
}
