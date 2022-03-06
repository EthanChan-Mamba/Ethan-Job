package com.ruoyi.business.futureTask;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.business.domain.ProjectItems;
import com.ruoyi.business.enums.ProjectStatus;
import com.ruoyi.business.service.IProjectItemsService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

/**
 * @author chen
 * @version 1.0.0
 * @ClassName FutureTask.java
 * @Description 集合查询
 * @createTime 2021-12-30 10:43
 */
@Slf4j
@Component
public class ProjectItemsFutureTask {
    @Resource
    private IProjectItemsService projectItemsService;

    /**
     * 异步操作任务调度线程池
     */
    private ThreadPoolTaskExecutor executor = SpringUtils.getBean("threadPoolTaskExecutor");

    @SuppressWarnings("all")
    public JSONObject getHomePageResult(ProjectItems projectItems) {
        log.info("首页查询");
        JSONObject jsonObject = new JSONObject();
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            QueryWrapper<ProjectItems> projectItemsQueryWrapper = new QueryWrapper<>();
            Future<Integer> pendingFuture = executor.submit(() -> projectItemsService.count(
                    projectItemsQueryWrapper.eq("create_by", loginUser.getUsername())
                    .eq("project_status", ProjectStatus.getCode("1"))
                    .eq("project_status", ProjectStatus.getCode("2"))
            ));

            //get阻塞
            Integer pending = pendingFuture.get();
            jsonObject.put("pending", pending);
        } catch (InterruptedException | ExecutionException e) {
            log.error(">>>>>>聚合查询首页信息异常:" + e + "<<<<<<<<<");
        }
        return jsonObject;
    }
}
