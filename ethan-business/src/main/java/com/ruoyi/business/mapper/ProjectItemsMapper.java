package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.ProjectItems;

import java.util.List;

/**
 * projectItemsMapper接口
 *
 * @author ruoyi
 * @date 2022-01-08
 */
public interface ProjectItemsMapper extends BaseMapper<ProjectItems>
{
    /**
     * 查询projectItems列表
     *
     * @param projectItems projectItems
     * @return projectItems集合
     */
    public List<ProjectItems> selectProjectItemsList(ProjectItems projectItems);

    /**
     * 查询projectItems列表
     *
     * @param projectItems projectItems
     * @return projectItems集合
     */
    public List<ProjectItems> selectProjectItemsListByLoginName(ProjectItems projectItems);

}
