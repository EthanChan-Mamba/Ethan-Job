package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 任务项目个体Service接口
 * 
 * @author chen
 * @date 2022-02-13
 */
public interface IProjectMissionItemService extends IService<ProjectMissionItem>
{
    /**
     * 查询任务项目个体列表
     * 
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体集合
     */
    public List<ProjectMissionItem> selectProjectMissionItemList(ProjectMissionItem projectMissionItem);

}
