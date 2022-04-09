package com.ruoyi.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.ProjectItems;

import java.util.List;

/**
 * projectItemsService接口
 *
 * @author ruoyi
 * @date 2022-01-08
 */
public interface IProjectItemsService extends IService<ProjectItems>
{
    /**
     * 查询projectItems列表
     *
     * @param projectItems projectItems
     * @return projectItems集合
     */
    public List<ProjectItems> selectProjectItemsList(ProjectItems projectItems);

    /**
     * 根据登录者查询projectItems列表
     *
     * @param projectItems projectItems
     * @return projectItems集合
     */
    public List<ProjectItems> selectProjectItemsListByLoginName(ProjectItems projectItems);
}
