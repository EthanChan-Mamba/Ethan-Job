package com.ruoyi.business.aspectj;

import com.ruoyi.business.annotation.ProjectLog;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.domain.ProjectNews;
import com.ruoyi.business.enums.BusinessTypeWithName;
import com.ruoyi.business.enums.MissionItemStatusEnum;
import com.ruoyi.business.service.IProjectNewsService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author chen
 * @version 1.0.0
 * @ClassName ProjectLogAspect.java
 * @Description 项目动态日志
 * @createTime 2022-02-28 09:35
 */
@Aspect
@Component
public class ProjectLogAspect {

    private static final Logger log = LoggerFactory.getLogger(ProjectLogAspect.class);

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
            BusinessTypeWithName businessTypeWithName = getServiceMthodTableType(joinPoint);
            StringBuilder newsContent = new StringBuilder();
            // 创建人
            newsContent.append(loginUser.getUsername());
            // 操作名称
            newsContent.append(businessTypeWithName.getInfo());
            newsContent.append("了任务：");
            ProjectMissionItem args = getArgs(joinPoint);
            if (args != null){
                // 任务名称
                newsContent.append(args.getMissionItemName()).append("; ");
            }
            System.out.println("----------------------------" + BusinessTypeWithName.UPDATE.getCode());
            System.out.println("----------------------------" + businessTypeWithName.getCode());
            if (BusinessTypeWithName.UPDATE.getCode().equals(businessTypeWithName.getCode())) {
                // 更新的状态
                assert args != null;
                newsContent.append("状态为：").append(MissionItemStatusEnum.getType(args.getMissionItemStatus())).append("; ");
                news.setUpdateBy(loginUser.getUsername());
                news.setUpdateTime(new Date());
            }
            newsContent.append(content);
            news.setProjectNewsContent(newsContent.toString());
            news.setCreateBy(loginUser.getUsername());
            news.setCreateTime(new Date());
            // 设置所属项目
            assert args != null;
            news.setProjectNewsName(args.getMissionItemName());
            news.setProjectId(args.getProjectId());
            projectNewsService.save(news);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
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
    private BusinessTypeWithName getServiceMthodTableType(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        BusinessTypeWithName businessTypeWithName = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    businessTypeWithName = method.getAnnotation(ProjectLog.class).logType();
                    break;
                }
            }
        }

        return businessTypeWithName;
    }

    /**
     *
     * @return
     */
    public ProjectMissionItem getArgs(JoinPoint joinPoint)
    {

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (i == 0) {
                return (ProjectMissionItem) args[0];
            } else {
                break;
            }
        }
        return null;
    }

}
