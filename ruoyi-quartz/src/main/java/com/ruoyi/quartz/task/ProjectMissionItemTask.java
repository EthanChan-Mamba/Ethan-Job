package com.ruoyi.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.enums.MissionItemStatusEnum;
import com.ruoyi.business.enums.ProjectStatusEnum;
import com.ruoyi.business.service.impl.ProjectMissionItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("projectMissionItemTask")
public class ProjectMissionItemTask
{
    @Autowired
    ProjectMissionItemServiceImpl projectMissionItemService;

    public void updateProjectMissionItemStatus() {
        List<ProjectMissionItem> list = projectMissionItemService.list();
        Date today = new Date();
        for (ProjectMissionItem projectMissionItem : list) {
            Integer missionItemStatus = projectMissionItem.getMissionItemStatus();
            if (ProjectStatusEnum.NOTBEGIN.getCode() == missionItemStatus) {
                Date finishTimeStart = projectMissionItem.getFinishTimeStart();
                int compare = DateUtil.compare(finishTimeStart, today);
                if (0 <= compare) {
                    projectMissionItem.setUpdateBy("admin");
                    projectMissionItem.setUpdateTime(today);
                    projectMissionItem.setMissionItemStatus(MissionItemStatusEnum.ONGOING.getCode());
                    projectMissionItemService.updateById(projectMissionItem);
                }
            } else if (ProjectStatusEnum.ONGOING.getCode() == missionItemStatus) {
                Date finishTimeEnd = projectMissionItem.getFinishTimeEnd();
                int compare = DateUtil.compare(finishTimeEnd, today);
                if (0 > compare) {
                    projectMissionItem.setUpdateBy("admin");
                    projectMissionItem.setUpdateTime(today);
                    projectMissionItem.setMissionItemStatus(MissionItemStatusEnum.TIMEOUT.getCode());
                    projectMissionItemService.updateById(projectMissionItem);
                }
            }
        }
    }
}
