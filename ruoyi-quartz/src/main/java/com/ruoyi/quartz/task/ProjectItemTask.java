package com.ruoyi.quartz.task;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.business.domain.ProjectItems;
import com.ruoyi.business.enums.ProjectStatusEnum;
import com.ruoyi.business.service.impl.ProjectItemsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("projectItemTask")
public class ProjectItemTask
{
    @Autowired
    ProjectItemsServiceImpl projectItemsService;

    public void updateProjectItemStatus() {
        List<ProjectItems> list = projectItemsService.list();
        Date today = new Date();
        for (ProjectItems projectItems : list) {
            Integer projectStatus = projectItems.getProjectStatus();
            if (ProjectStatusEnum.NOTBEGIN.getCode() == projectStatus) {
                Date finishTimeStart = projectItems.getFinishTimeStart();
                int compare = DateUtil.compare(finishTimeStart, today);
                if (0 <= compare) {
                    projectItems.setUpdateBy("admin");
                    projectItems.setUpdateTime(today);
                    projectItems.setProjectStatus(ProjectStatusEnum.ONGOING.getCode());
                    projectItemsService.updateById(projectItems);
                }
            } else if (ProjectStatusEnum.ONGOING.getCode() == projectStatus) {
                Date finishTimeEnd = projectItems.getFinishTimeEnd();
                int compare = DateUtil.compare(finishTimeEnd, today);
                if (0 > compare) {
                    projectItems.setUpdateBy("admin");
                    projectItems.setUpdateTime(today);
                    projectItems.setProjectStatus(ProjectStatusEnum.TIMEOUT.getCode());
                    projectItemsService.updateById(projectItems);
                }
            }
        }
    }
}
