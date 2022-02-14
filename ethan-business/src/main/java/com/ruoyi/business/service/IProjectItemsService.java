package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.ProjectItems;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
