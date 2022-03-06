package com.ruoyi.business.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.ProjectNews;
import com.ruoyi.business.service.IProjectNewsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目动态Controller
 *
 * @author chen
 * @date 2022-02-27
 */
@Controller
@RequestMapping("/ethan-business/project-news")
public class ProjectNewsController extends BaseController
{
    private String prefix = "ethan-business/project-news";

    @Autowired
    private IProjectNewsService projectNewsService;

    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:view')")
    @GetMapping()
    public String projectNews()
    {
        return prefix + "/project-news";
    }

    /**
     * 查询项目动态列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectNews projectNews)
    {
        startPage();
        List<ProjectNews> list = projectNewsService.selectProjectNewsList(projectNews);
        return getDataTable(list);
    }

    /**
     * 导出项目动态列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:export')")
    @Log(title = "项目动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectNews projectNews)
    {
        List<ProjectNews> list = projectNewsService.selectProjectNewsList(projectNews);
        ExcelUtil<ProjectNews> util = new ExcelUtil<ProjectNews>(ProjectNews.class);
        return util.exportExcel(list, "项目动态数据");
    }

    /**
     * 新增项目动态
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目动态
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:add')")
    @Log(title = "项目动态", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProjectNews projectNews)
    {
        return toAjax(projectNewsService.save(projectNews));
    }

    /**
     * 修改项目动态
     */
    @GetMapping("/edit/{newsId}")
    public String edit(@PathVariable("newsId") Integer newsId, ModelMap mmap)
    {
        ProjectNews projectNews = projectNewsService.getById(newsId);
        mmap.put("projectNews", projectNews);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目动态
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:edit')")
    @Log(title = "项目动态", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectNews projectNews)
    {
        return toAjax(projectNewsService.updateById(projectNews));
    }

    /**
     * 删除项目动态
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:project-news:remove')")
    @Log(title = "项目动态", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectNewsService.removeByIds(Arrays.asList(ids.split(","))));
    }
}
