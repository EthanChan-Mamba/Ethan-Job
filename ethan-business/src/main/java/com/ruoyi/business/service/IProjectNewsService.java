package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.ProjectNews;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 项目动态Service接口
 * 
 * @author chen
 * @date 2022-02-27
 */
public interface IProjectNewsService extends IService<ProjectNews>
{
    /**
     * 查询项目动态列表
     * 
     * @param projectNews 项目动态
     * @return 项目动态集合
     */
    public List<ProjectNews> selectProjectNewsList(ProjectNews projectNews);

}
