package com.ruoyi.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.mapper.ProjectMissionItemMapper;
import com.ruoyi.business.service.IProjectMissionItemService;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务项目个体Service业务层处理
 *
 * @author chen
 * @date 2022-02-13
 */
@Service
public class ProjectMissionItemServiceImpl extends ServiceImpl<ProjectMissionItemMapper, ProjectMissionItem> implements IProjectMissionItemService
{
    @Autowired
    private ProjectMissionItemMapper projectMissionItemMapper;

    /**
     * 查询任务项目个体列表
     *
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体
     */
    @Override
    public List<ProjectMissionItem> selectProjectMissionItemList(ProjectMissionItem projectMissionItem)
    {
        return projectMissionItemMapper.selectProjectMissionItemList(projectMissionItem);
    }

    /**
     * 根据userid查询任务项目个体列表
     *
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体
     */
    @Override
    public List<ProjectMissionItem> selectProjectMissionItemListByUserid(ProjectMissionItem projectMissionItem)
    {
        projectMissionItem.setCreateBy(SecurityUtils.getLoginUser().getUsername());
        return projectMissionItemMapper.selectProjectMissionItemList(projectMissionItem);
    }

}
