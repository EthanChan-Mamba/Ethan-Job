package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 任务项目个体Mapper接口
 * 
 * @author chen
 * @date 2022-02-13
 */
public interface ProjectMissionItemMapper extends BaseMapper<ProjectMissionItem>
{
    /**
     * 查询任务项目个体列表
     * 
     * @param projectMissionItem 任务项目个体
     * @return 任务项目个体集合
     */
    public List<ProjectMissionItem> selectProjectMissionItemList(ProjectMissionItem projectMissionItem);

}
