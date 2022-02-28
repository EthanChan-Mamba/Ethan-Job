package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.ProjectNewsMapper;
import com.ruoyi.business.domain.ProjectNews;
import com.ruoyi.business.service.IProjectNewsService;
import com.ruoyi.common.core.text.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 项目动态Service业务层处理
 * 
 * @author chen
 * @date 2022-02-27
 */
@Service
public class ProjectNewsServiceImpl extends ServiceImpl<ProjectNewsMapper, ProjectNews> implements IProjectNewsService
{
    @Autowired
    private ProjectNewsMapper projectNewsMapper;

    /**
     * 查询项目动态列表
     * 
     * @param projectNews 项目动态
     * @return 项目动态
     */
    @Override
    public List<ProjectNews> selectProjectNewsList(ProjectNews projectNews)
    {
        return projectNewsMapper.selectProjectNewsList(projectNews);
    }

}
