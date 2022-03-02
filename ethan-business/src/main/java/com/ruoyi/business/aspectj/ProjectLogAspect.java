package com.ruoyi.business.aspectj;

import com.alibaba.fastjson.JSON;
import com.ruoyi.business.annotation.ProjectLog;
import com.ruoyi.business.domain.ProjectNews;
import com.ruoyi.business.service.IProjectNewsService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.HttpMethod;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * @author chen
 * @version 1.0.0
 * @ClassName SystemLogAspect.java
 * @Description TODO
 * @createTime 2022-02-28 09:35
 */
@Aspect
@Component
public class ProjectLogAspect {

    @Autowired
    private IProjectNewsService projectNewsService;

    @Pointcut("@annotation(com.ruoyi.business.annotation.ProjectLog)")
    public void serviceAspect() {

    }


    @After("serviceAspect()")
    public void doServiceLog(JoinPoint joinPoint) {
        // 获取用户信息
        LoginUser loginUser = SecurityUtils.getLoginUser();

        ProjectNews news = new ProjectNews();

        try {
            String content = getServiceMthodDescription(joinPoint);
            String serviceMthodTableType = getServiceMthodTableType(joinPoint).name();
            news.setProjectNewsContent(loginUser.getUsername() + serviceMthodTableType + content);
            news.setCreateBy(loginUser.getUsername());
            getArgs(joinPoint);
            // projectNewsService.save(news);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws ClassNotFoundException
     */
    private String getServiceMthodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ProjectLog.class).description();
                    break;
                }
            }
        }

        return description;
    }

    /**
     * 获取注解方法中的描述信息  用于 Service 层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws ClassNotFoundException
     */
    private BusinessType getServiceMthodTableType(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        BusinessType description = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(ProjectLog.class).logType();
                    break;
                }
            }
        }

        return description;
    }

    /**
     *
     */
    public void getArgs(JoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();
        System.out.println(Arrays.toString(args));
    }

    /**
     * 获取json格式的参数<br>
     *
     * @param joinPoint
     * @return
     * @since JDK1.8
     */
    // private String getServiceMethodParams(JoinPoint joinPoint) {
    //     Object[] arguments = joinPoint.getArgs();
    //     if (arguments == null || arguments.length == 0) {
    //         return "无参数";
    //     }
    //     List<Object> list = new ArrayList<>(Arrays.asList(arguments));
    //     String params = JsonUtil.getJsonStringFromPOJO(list);
    //     JSONObject.toJSONString()
    //     return params;
    // }
}
