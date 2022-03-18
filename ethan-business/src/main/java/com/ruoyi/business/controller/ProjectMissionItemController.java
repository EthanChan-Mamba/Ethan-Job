package com.ruoyi.business.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.business.annotation.ProjectLog;
import com.ruoyi.business.enums.BusinessTypeWithName;
import com.ruoyi.business.futureTask.ProjectItemsFutureTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.ProjectMissionItem;
import com.ruoyi.business.service.IProjectMissionItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 任务项目个体Controller
 *
 * @author chen
 * @date 2022-02-13
 */
@Controller
@RequestMapping("/ethan-business/ProjectMissionItem")
public class ProjectMissionItemController extends BaseController
{
    private String prefix = "ethan-business/ProjectMissionItem";

    @Autowired
    private IProjectMissionItemService projectMissionItemService;
    private ProjectItemsFutureTask projectItemsFutureTask;
    @Autowired
    public void setProjectItemsFutureTask(ProjectItemsFutureTask projectItemsFutureTask) {
        this.projectItemsFutureTask = projectItemsFutureTask;
    }

    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:view')")
    @GetMapping()
    public String ProjectMissionItem()
    {
        return prefix + "/ProjectMissionItem";
    }

    /**
     * 查询任务项目个体列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:list')")
    @GetMapping("/list")
    @ResponseBody
    public AjaxResult list(ProjectMissionItem projectMissionItem)
    {
        JSONObject homePageResult = projectItemsFutureTask.getHomePageCardResult(projectMissionItem);
        return AjaxResult.success(homePageResult);
    }

    /**
     * 查询任务项目个体列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo listNumsWithMissionList(ProjectMissionItem projectMissionItem)
    {
        startPage();
        List<ProjectMissionItem> list = projectMissionItemService.selectProjectMissionItemList(projectMissionItem);
        return getDataTable(list);
    }

    /**
     * 导出任务项目个体列表
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:export')")
    @Log(title = "任务项目个体", businessType = BusinessType.EXPORT)
    @ProjectLog(description = "", logType = BusinessTypeWithName.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProjectMissionItem projectMissionItem)
    {
        List<ProjectMissionItem> list = projectMissionItemService.selectProjectMissionItemList(projectMissionItem);
        ExcelUtil<ProjectMissionItem> util = new ExcelUtil<ProjectMissionItem>(ProjectMissionItem.class);
        return util.exportExcel(list, "任务项目个体数据");
    }

    /**
     * 新增任务项目个体
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:add')")
    @Log(title = "任务项目个体", businessType = BusinessType.INSERT)
    @ProjectLog(description = "", logType = BusinessTypeWithName.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody ProjectMissionItem projectMissionItem)
    {
        projectMissionItem.setMissionItemStatus(1);
        projectMissionItem.setCreateBy(getUsername());
        projectMissionItem.setCreateTime(new Date());
        return toAjax(projectMissionItemService.save(projectMissionItem));
    }

    /**
     * 修改任务项目个体
     */
    @GetMapping("/edit/{missionItemId}")
    public String edit(@PathVariable("missionItemId") Long missionItemId, ModelMap mmap)
    {
        ProjectMissionItem projectMissionItem = projectMissionItemService.getById(missionItemId);
        mmap.put("projectMissionItem", projectMissionItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:edit')")
    @Log(title = "任务项目个体", businessType = BusinessType.UPDATE)
    @ProjectLog(description = "", logType = BusinessTypeWithName.UPDATE)
    @PutMapping
    @ResponseBody
    public AjaxResult editSave(@RequestBody ProjectMissionItem projectMissionItem)
    {
        projectMissionItem.setUpdateBy(getUsername());
        projectMissionItem.setUpdateTime(new Date());
        return toAjax(projectMissionItemService.updateById(projectMissionItem));
    }

    /**
     * 删除任务项目个体
     */
    @PreAuthorize("@ss.hasPermi('ethan-business:ProjectMissionItem:remove')")
    @Log(title = "任务项目个体", businessType = BusinessType.DELETE)
    @ProjectLog(description = "", logType = BusinessTypeWithName.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectMissionItemService.removeByIds(Arrays.asList(ids.split(","))));
    }
}
