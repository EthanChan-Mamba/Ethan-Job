package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.ProjectMissionItemMapper;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.service.IProjectMissionItemService;
import com.ruoyi.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

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

}
