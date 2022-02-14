package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.ProjectItems;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

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

}
