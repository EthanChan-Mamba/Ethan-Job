package com.ruoyi.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.business.domain.ProjectItems;
import com.ruoyi.business.futureTask.ProjectItemsFutureTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.service.IProjectItemsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * projectItemsController
 *
 * @author ruoyi
 * @date 2022-01-08
 */
@Controller
@RequestMapping("/ethan-business/projectItems")
public class ProjectItemsController extends BaseController
{
    private String prefix = "ethan-business/projectItems";

    @Autowired
    private IProjectItemsService projectItemsService;

    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:view')")
    @GetMapping()
    public String projectItems()
    {
        return prefix + "/projectItems";
    }

    /**
     * 查询projectItems列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProjectItems projectItems)
    {
        startPage();
        List<ProjectItems> list = projectItemsService.selectProjectItemsList(projectItems);
        return getDataTable(list);
    }

    /**
     * 查询projectItems列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:list')")
    @GetMapping("/listNoPage")
    @ResponseBody
    public TableDataInfo listNoPage(ProjectItems projectItems)
    {
        List<ProjectItems> list = projectItemsService.selectProjectItemsList(projectItems);
        return getDataTable(list);
    }

    /**
     * 导出projectItems列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:export')")
    @Log(title = "projectItems", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectItems projectItems)
    {
        List<ProjectItems> list = projectItemsService.selectProjectItemsList(projectItems);
        ExcelUtil<ProjectItems> util = new ExcelUtil<ProjectItems>(ProjectItems.class);
        return util.exportExcel(list, "projectItems数据");
    }

    /**
     * 新增projectItems
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存projectItems
     * TODO:根据开始时间判断项目状态，未到开始时间的项目存到缓存中，到时间再发出开始消息且更改项目状态
     */
    // @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:add')")
    @Log(title = "projectItems", businessType = BusinessType.INSERT)
    // @PostMapping("/add")
    @PostMapping
    @ResponseBody
    public AjaxResult addSave(@RequestBody ProjectItems projectItems)
    {
        projectItems.setProjectStatus(1);
        projectItems.setProjectType(0);
        projectItems.setCreateBy(getUsername());
        projectItems.setCreateTime(new Date());
        return toAjax(projectItemsService.save(projectItems));
        // return toAjax(true);
    }

    /**
     * 修改projectItems
     */
    @GetMapping("/edit/{projectId}")
    public String edit(@PathVariable("projectId") Long projectId, ModelMap mmap)
    {
        ProjectItems projectItems = projectItemsService.getById(projectId);
        mmap.put("projectItems", projectItems);
        return prefix + "/edit";
    }

    /**
     * 修改保存projectItems
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:edit')")
    @Log(title = "projectItems", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProjectItems projectItems)
    {
        return toAjax(projectItemsService.updateById(projectItems));
    }

    /**
     * 删除projectItems
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:projectItems:remove')")
    @Log(title = "projectItems", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectItemsService.removeByIds(Arrays.asList(ids.split(","))));
    }
}
