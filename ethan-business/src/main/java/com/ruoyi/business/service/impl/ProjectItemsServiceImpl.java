package com.ruoyi.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.ProjectItemsMapper;
import com.ruoyi.business.domain.ProjectItems;
import com.ruoyi.business.service.IProjectItemsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * projectItemsService业务层处理
 *
 * @author ruoyi
 * @date 2022-01-08
 */
@Service
public class ProjectItemsServiceImpl extends ServiceImpl<ProjectItemsMapper, ProjectItems> implements IProjectItemsService
{
    @Autowired
    private ProjectItemsMapper projectItemsMapper;

    /**
     * 查询projectItems列表
     *
     * @param projectItems projectItems
     * @return projectItems
     */
    @Override
    public List<ProjectItems> selectProjectItemsList(ProjectItems projectItems)
    {
        return projectItemsMapper.selectProjectItemsList(projectItems);
    }

}
