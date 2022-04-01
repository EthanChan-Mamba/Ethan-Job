package com.ruoyi.business.futureTask;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.enums.MissionItemStatusEnum;
import com.ruoyi.business.service.IProjectMissionItemService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    private IProjectMissionItemService projectMissionItemService;

    /**
     * 异步操作任务调度线程池
     */
    private ThreadPoolTaskExecutor executor = SpringUtils.getBean("threadPoolTaskExecutor");

    @SuppressWarnings("all")
    public JSONObject getHomePageCardResult() {
        log.info("首页查询");
        JSONObject jsonObject = new JSONObject();
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            QueryWrapper<ProjectMissionItem> projectMissionItemQueryWrapper = new QueryWrapper<>();
            List<Integer> missionItemStatusList = new ArrayList<>();

            // 今日待完成
            missionItemStatusList.add(MissionItemStatusEnum.getCode("进行中"));
            missionItemStatusList.add(MissionItemStatusEnum.getCode("超时"));
            missionItemStatusList.add(MissionItemStatusEnum.getCode("延期"));
            Future<Long> pendingFuture = executor.submit(() -> projectMissionItemService.count(
                    projectMissionItemQueryWrapper.eq("create_by", loginUser.getUsername())
                    .in("mission_item_status", missionItemStatusList)
            ));
            projectMissionItemQueryWrapper.clear();
            //get阻塞
            Long pending = pendingFuture.get();
            jsonObject.put("pending", pending);

            // 超时未完成
            missionItemStatusList.remove(MissionItemStatusEnum.getCode("进行中"));
            Future<Long> timeoutFuture = executor.submit(() -> projectMissionItemService.count(
                    projectMissionItemQueryWrapper.eq("create_by", loginUser.getUsername())
                    .in("mission_item_status", missionItemStatusList)
            ));
            projectMissionItemQueryWrapper.clear();
            Long timeout = timeoutFuture.get();
            jsonObject.put("timeout", timeout);

            // 总任务数
            Future<Long> participateFuture = executor.submit(() -> projectMissionItemService.count(
                    projectMissionItemQueryWrapper.eq("create_by", loginUser.getUsername())
            ));
            projectMissionItemQueryWrapper.clear();
            Long participate = participateFuture.get();
            jsonObject.put("participate", participate);

        } catch (InterruptedException | ExecutionException e) {
            log.error(">>>>>>聚合查询首页信息异常:" + e + "<<<<<<<<<");
        }
        return jsonObject;
    }
}
