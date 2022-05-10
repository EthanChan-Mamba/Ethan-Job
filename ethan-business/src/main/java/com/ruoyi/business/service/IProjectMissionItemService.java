package com.ruoyi.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.ProjectMissionItem;

import java.util.List;

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

    /**
     * 根据userid查询任务项目个体列表
     *
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体集合
     */
    public List<ProjectMissionItem> selectProjectMissionItemListByUserid(ProjectMissionItem projectMissionItem);

    /**
     * 根据userid和状态值查询任务项目个体列表
     *
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体集合
     */
    public List<ProjectMissionItem> homepageListMissionListByUserid(ProjectMissionItem projectMissionItem);
}
